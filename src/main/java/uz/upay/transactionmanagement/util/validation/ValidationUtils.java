package uz.upay.transactionmanagement.util.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static Pattern phoneNumberPattern = Pattern.compile("^998(9[012345789]|6[125679]|7[01234569])[0-9]{7}$");
    private static Pattern rgbPatternPattern = Pattern.compile("[a-fA-F\\d]{6}");

//    public static void validatePhoneNumber(String phoneNumber) {
//        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);

}
