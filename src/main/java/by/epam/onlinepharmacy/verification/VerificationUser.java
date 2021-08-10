//package by.epam.onlinepharmacy.verification;
//
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.io.File;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Properties;
//
//public class VerificationUser {
//    static {
//
//        System.setProperty("javax.net.ssl.trustStore", "C://Users//Nastya//Downloads//mytruststore");
//        System.setProperty("javax.net.ssl.trustStorePassword", "kristina213+");
//
//    }
//    public static void getVerificationUser() throws MessagingException, NoSuchProviderException {
////        final Properties properties = new Properties();
////        try {
////            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties"));
//////        properties.put("javax.net.ssl.trustStore", "C://Users//Nastya//Downloads//mytruststore");
//////        properties.put("javax.net.ssl.trustStorePassword", "kristina213+");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        Session mailSession = Session.getDefaultInstance(properties);
////        MimeMessage message = new MimeMessage(mailSession);
////        try {
////            message.setFrom(new InternetAddress("testpostepam@gmail.com"));
////        } catch (MessagingException e) {
////            e.printStackTrace();
////        }
////        try {
////            message.addRecipient(Message.RecipientType.TO, new InternetAddress("realovka@mail.ru"));
////            message.setSubject("Hello");
////            message.setText("My message");
////        } catch (MessagingException e) {
////            e.printStackTrace();
////        }
////
////        try {
////            Transport tr = mailSession.getTransport();
////            tr.connect(null, "yrhkoesdpjhtxrpm");
////            tr.sendMessage(message, message.getAllRecipients());
////            tr.close();
////        } catch (NoSuchProviderException e) {
////            e.printStackTrace();
////        }
////    }
////        String d_email = "testpostepam@gmail.com",
////                d_uname = "Name",
////                d_password = "kristina213+",
////                d_host = "smtp.gmail.com",
////                d_port = "465",
////                m_to = "realovka@mail.ru",
////                m_subject = "Indoors Readable File: ",
////                m_text = "This message is from Indoor Positioning App. Required file(s) are attached.";
////        Properties props = new Properties();
////        props.put("javax.net.ssl.trustStore", "C://Users//Nastya//Downloads//mytruststore");
////        props.put("javax.net.ssl.trustStorePassword", "kristina213+");
////        props.put("mail.smtp.user", d_email);
////        props.put("mail.smtp.host", d_host);
////        props.put("mail.smtp.port", d_port);
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.debug", "true");
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.socketFactory.port", d_port);
////        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
////        props.put("mail.smtp.socketFactory.fallback", "false");
////
//////        SMTPAuthenticator auth = new SMTPAuthenticator();
////        Session session = Session.getDefaultInstance(props);
////        session.setDebug(true);
////
////        MimeMessage msg = new MimeMessage(session);
////        try {
////            msg.setSubject(m_subject);
////            msg.setFrom(new InternetAddress(d_email));
////            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
////
////            Transport transport = session.getTransport("smtps");
////            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
////            transport.sendMessage(msg, msg.getAllRecipients());
////            transport.close();
////
////        } catch (AddressException e) {
////            e.printStackTrace();
////
////        } catch (MessagingException e) {
////            e.printStackTrace();
////
////        }
//
////    }
//
//
//            try {
//                HttpURLConnection connection = (HttpURLConnection) new URL("https://gmail.com").openConnection();
//                int responseCode = connection.getResponseCode();
//                if(responseCode != 200){
//                    System.out.println("Test failed with actual status code: " + responseCode);
//                }else{
//                    System.out.println("Test passed");
//                }
//            } catch (IOException e) {
//                System.out.println("Test failed with exception: " + e.getMessage());
//            }
//
//        }
//
//    }
