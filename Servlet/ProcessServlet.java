import jakarta.servlet.*;
import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import java.io.IOException;

public class ProcessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String text = request.getParameter("text");
        String algorithm = request.getParameter("algorithm");
        String key = request.getParameter("key");
        String operation = request.getParameter("operation");

        System.out.println("Text: " + text);
        System.out.println("Algorithm: " + algorithm);
        System.out.println("Key: " + key);
        System.out.println("Operation: " + operation);

        if (text == null || algorithm == null || operation == null || key == null) {
            response.setContentType("text/plain");
            response.getWriter().println("Missing input values!");
            return;
        }
        
        String result = "";

        try {
            switch (algorithm) {
                case "caesar":
                    try {
                        int shift = Integer.parseInt(key);
                        result = operation.equals("encrypt")
                                ? EncryptionDecryption.caesarEncrypt(text, shift)
                                : EncryptionDecryption.caesarDecrypt(text, shift);
                    } catch (NumberFormatException e) {
                        result = "Invalid Caesar Key: must be an integer.";
                    }
                    break;

                case "vigenere":
                    result = operation.equals("encrypt") ? EncryptionDecryption.vigenereEncrypt(text, key)
                            : EncryptionDecryption.vigenereDecrypt(text, key);
                    break;

                case "base64":
                    result = operation.equals("encrypt") ? EncryptionDecryption.base64Encode(text)
                            : EncryptionDecryption.base64Decode(text);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "An error occurred during processing.";
        }

        // Set response content type to 'text/html' so the result can be parsed
        response.setContentType("text/plain");
        response.getWriter().write(result); // Send the result as plain text
    }
}