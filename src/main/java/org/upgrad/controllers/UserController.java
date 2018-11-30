package org.upgrad.controllers;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.upgrad.models.User;
import org.upgrad.services.UserAuthTokenService;
import org.upgrad.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthTokenService userAuthTokenService;

    /*
     * This endpoint is to handle new user signup.
     *
     */
    @PostMapping("/signup")
    @CrossOrigin
    public ResponseEntity<?> signup(@RequestParam String firstName, String lastName, @RequestParam String email, @RequestParam String contactNumber, @RequestParam String password) {
        if (userService.findUser(contactNumber)!=null) {
            return new ResponseEntity<>("Try any other contact number, this contact number has already been registered!",HttpStatus.BAD_REQUEST);
        }
        else if (!isEmailValid(email)) {
            return new ResponseEntity<>("Invalid email-id format!", HttpStatus.BAD_REQUEST);
        }
        else if (contactNumber.length()!=10 || !isContactNumberValid(contactNumber)) {
            return new ResponseEntity<>("Invalid contact number!", HttpStatus.BAD_REQUEST);
        }
        else if (!isPasswordStrong(password)) {
            return  new ResponseEntity<>("Weak password!", HttpStatus.BAD_REQUEST);
        }
        else {
            String sha256hex = Hashing.sha256()
                    .hashString(password, Charsets.US_ASCII)
                    .toString();
            userService.newUser(firstName, lastName, email, contactNumber, sha256hex);
            return new ResponseEntity<>("User with contact number " + contactNumber + " successfully registered!", HttpStatus.CREATED);
        }
    }

    /*
    * This endpoint is used to login a user.
    * Here contact number and password has to be provided to match the credentials.
    */
    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestParam String contactNumber, @RequestParam String password){
        String passwordByUser = String.valueOf(userService.findUserPassword(contactNumber));
        String sha256hex = Hashing.sha256()
                .hashString(password, Charsets.US_ASCII)
                .toString();
        if(userService.findUserPassword(contactNumber)==null) return new ResponseEntity<>("This contact number has not been registered!",HttpStatus.OK);
        else if (!(passwordByUser.equalsIgnoreCase(sha256hex))) {
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }
        else{
            User user = userService.findUser(contactNumber);
            String accessToken = UUID.randomUUID().toString();
            userAuthTokenService.addAccessToken(user.getId(),accessToken);
            HttpHeaders headers = new HttpHeaders();
            headers.add("access-token", accessToken);
            List<String> header = new ArrayList<>();
            header.add("access-token");
            headers.setAccessControlExposeHeaders(header);
            return new ResponseEntity<>(user,headers,HttpStatus.OK);
        }
    }

    /*
    * This endpoint is used to logout a user.
    * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
    */
    @PutMapping("/logout")
    @CrossOrigin
    public ResponseEntity<String> logout(@RequestHeader String accessToken){
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }
        else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else{
            userAuthTokenService.removeAccessToken(accessToken);
            return new ResponseEntity<>("You have logged out successfully!",HttpStatus.OK);}
    }

    /*
     * This endpoint is used to update user first name and last name
     * Authentication is required to access this endpoint, so accessToken is taken as request header to make sure user is authenticated.
     */
    @PutMapping("/user")
    @CrossOrigin
    public ResponseEntity<?> updateUserName (@RequestParam String firstName, String lastName, @RequestHeader String accessToken) {
        if(userAuthTokenService.isUserLoggedIn(accessToken) == null){
            return new ResponseEntity<>("Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        } else if(userAuthTokenService.isUserLoggedIn(accessToken).getLogoutAt()!=null){
            return new ResponseEntity<>("You have already logged out. Please Login first to access this endpoint!", HttpStatus.UNAUTHORIZED);
        }  else {
            int userId = userAuthTokenService.getUserIdByAuth(accessToken);
            userService.editUser(firstName, lastName, userId);
            User user = userService.findUserById (userId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }


    /*
    * Using Regex and pattern matcher to check email validity
    */
    public static boolean isEmailValid (String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /*
     * Using Regex and pattern matcher to check contact number validity
     */
    public static boolean isContactNumberValid (String contactNumber) {
        try {
            Integer.parseInt(contactNumber);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    /*
     * Using Regex and pattern matcher to check password strength
     */
    public static boolean isPasswordStrong (String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[#@$%&*!^]).{8,}$";
        Pattern pat = Pattern.compile(passwordRegex );
        if (password == null)
            return false;

        return pat.matcher(password).matches();
    }
}
