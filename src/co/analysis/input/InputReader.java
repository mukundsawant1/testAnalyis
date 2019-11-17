package co.analysis.input;

import co.analysis.exceptions.BlankDateException;
import co.analysis.exceptions.DateInvalidFormatException;
import co.analysis.output.ConsoleMessages;
import co.analysis.model.FilterParam;
import co.analysis.utils.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class InputReader {
    public static FilterParam readFilterParamsFromUser() throws DateInvalidFormatException, IOException, BlankDateException {
        showUserMessages();
        return readUserInput();
    }

    public static FilterParam readUserInput() throws IOException, BlankDateException, DateInvalidFormatException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(ConsoleMessages.SHOW_FROM_DATE_MESSAGE);
            Date fromDate = CommonUtils.stringToDateConvert(br.readLine());
            System.out.println(ConsoleMessages.SHOW_TO_DATE_MESSAGE);
            Date toDate = CommonUtils.stringToDateConvert(br.readLine());
            System.out.println(ConsoleMessages.SHOW_MERCHANT_NAME_MESSAGE);
            String merchantName = br.readLine().toUpperCase();
            return new FilterParam(fromDate, toDate, merchantName);
        }

    }

    public static void showUserMessages() {

        System.out.println(ConsoleMessages.WELCOME_MESSAGE);
        System.out.println(ConsoleMessages.INFO_INPUT_MESSAGE);
        System.out.println(ConsoleMessages.ALLOWED_DATE_FORMT_MESSAGE);

    }
}
