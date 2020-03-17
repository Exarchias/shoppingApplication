package com.example.shoppingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingapplication.gmailSender.GmailSender;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class ForgetPasswordActivity extends AppCompatActivity {

    private MailSender mailSender;

    private EditText emailEditText;
    private Button sendToEmail;
    GmailSender gmailSender;
    // new ones
    public String jobNo;
    public String teamNo;
    private static final String username = "berheaklilu1@gmail.com";
    private static final String password = "Eritrea17";
    private static final String emailid = "mail2@outlook.com";
    private static final String subject = "Photo";
    private static final String message = "Hello";
    private Multipart multipart = new MimeMultipart();
    private MimeBodyPart messageBodyPart = new MimeBodyPart();
    public File mediaFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        emailEditText = findViewById(R.id.email_edit_text);
        sendToEmail = findViewById(R.id.send_to_email);

        //new ones

        Intent intent = getIntent();
        jobNo = intent.getStringExtra("Job_No");
        teamNo = intent.getStringExtra("Team_No");
        //sendMail(emailid, subject, message);


        sendToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sendMail(emailid, subject, message);

                /*try {

                    String email = "berheaklilu1@gmail.com";  // temp gmail account to send mails from
                    String pass = "Eritrea17";
                    GmailSender gmailSender = new GmailSender(email,pass);
                    gmailSender.sendMail("test", "Hello gmail","berheaklilu1@gmail.com", "karl.i.lundh@gmail.com");
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }

        });

    }

  /*  private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();

        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private Session createSessionObject() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");//"smtp.mailtrap.io";
        properties.put("mail.smtp.port", "587");

        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private Message createMessage(String email, String subject, String messageBody, Session session) throws
            MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("berheaklilu1@gmail.com", "Naveed Qureshi"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email, email));
        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }

    public class SendMailTask extends AsyncTask<Message, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(ForgetPasswordActivity.this, "Please wait", "Sending mail", true, false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }
        protected Void doInBackground(javax.mail.Message... messages) {
            try {
                Transport.send(messages[0]);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        }

    }*/
}
