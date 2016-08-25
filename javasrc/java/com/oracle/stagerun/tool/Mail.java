package com.oracle.stagerun.tool;





import com.oracle.stagerun.entity.RegressDetails;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


public class Mail
{
   StringBuffer strBuffer;
   private RegressDetails regressDetail;
   
   private StageRun sr;
   
   public Mail () {
       sr= StageRun.getInstance();               
   }
   
  // private int numberOfJobs;
   public Mail(RegressDetails prop)
   {
      this.regressDetail = prop;
      strBuffer = new StringBuffer();
      strBuffer.append("<HTML>\n");
      strBuffer.append("    <HEAD>\n");
      strBuffer.append("        <TITLE> New Document </TITLE>\n");
      strBuffer.append("        <style>\n");
      strBuffer.append("            table {\n");
      strBuffer.append("                border-collapse:collapse;\n");
      strBuffer.append("                padding:0in 1.0pt 0in 1.0pt;\n");
      strBuffer.append("            }\n");
      strBuffer.append("            \n");
      strBuffer.append("            td{\n");
      strBuffer.append("                width: auto;\n");
      strBuffer.append("                border:solid white 1.0pt;\n");
      strBuffer.append("               \n");
      strBuffer.append("            }\n");

      strBuffer.append("            tbody{\n");
      strBuffer.append("                background:#A5D5E2;\n");
      strBuffer.append("                color: black;\n");
      strBuffer.append("            }\n");

      strBuffer.append("            caption{\n");
      strBuffer.append("               border:solid black 1.0pt;  \n");
      strBuffer.append("                font-size: 1.5em;\n");
      strBuffer.append("                 background:#A5D5E2;word-break:break-all;\n");
      strBuffer.append("             }\n");

      strBuffer.append("            #td1 {\n");
      strBuffer.append("                width:10%;\n");
      strBuffer.append("                background:#A5D5E2;word-break:break-all;\n");
      strBuffer.append("            }\n");

      strBuffer.append("            #td2 {\n");
      strBuffer.append("                width:30%;\n");
      strBuffer.append("                background:#A5D5E2;word-break:break-all;\n");
      strBuffer.append("            }\n");

      strBuffer.append("            #td3 {\n");
      strBuffer.append("                width:30%;\n");
      strBuffer.append("                background:#A5D5E2;word-break:break-all;\n");
      strBuffer.append("            }\n");

      strBuffer.append("            \n");
      strBuffer.append("            thead,tfoot{\n");
      strBuffer.append("                background:#4BACC6;\n");
      strBuffer.append("                color:white;\n");
      strBuffer.append("            }\n");
      strBuffer.append("        </style>\n");

      strBuffer.append("    </HEAD>\n");
      strBuffer.append("    <BODY>\n");
   }

   public void sendMail()
   {
      generateContent();
      // Recipient's email ID needs to be mentioned.
      String to = regressDetail.getTestunit().getEmails();//StageRun.versionProperties.getProperty("GROUP_EMAIL");

      // Sender's email ID needs to be mentioned
      String from = "madhu.seelam@oracle.com";

      // Assuming you are sending email from localhost
      String host = "localhost";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("stbeehive.oracle.com", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try
      {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         String subject = "StageRunner: Results for stage:" + regressDetail.getStage().getStageName() + " Release: " + 
                 regressDetail.getStage().getRelease().getName() + " " + regressDetail.getTestunit().getPlatform().getName() + " "
                 + regressDetail.getTestunit().getTestunitName();     
         
         String yourEncodedString = MimeUtility.encodeText(subject, "UTF-8", "B");

         //message.setSubject(yourEncodedString);
         message.setHeader("Subject", yourEncodedString);
         // Now set the actual message
         message.setText(strBuffer.toString(), "UTF-8", "html");

         // Send message
         Transport.send(message);
         //sr.print("Sent message successfully....",regressDetail);
      }
      catch (Exception e)
      {
         sr.print("Exception : Sending mail:" + e);
      }
   }

   public void generateContent()
   {
      try
      {
         //int numberOfJobs = Integer.parseInt(prop.getProperty("numberOfJobs"));
         //int numberOfFarmJobs = Integer.parseInt(prop.getProperty("numberOfFarmJobs"));

         String caption = "Stage:" + regressDetail.getStage().getStageName() + "<br>Release: " + regressDetail.getStage().getRelease().getName();

         strBuffer.append("    <table>\n");
         strBuffer.append("       <CAPTION><b><i>" + caption + "</i><b></CAPTION>\n");         
         strBuffer.append("          <tr><td>URL</td> <td><i>http://slc09iyd.us.oracle.com:8080/sr</i></td></tr>\n");
         strBuffer.append("          <tr><td>TestUnit</td> <td>"+ regressDetail.getTestunit().getTestunitName() +"</td></tr>\n");
         strBuffer.append("          <tr><td>Stage</td> <td>"+ regressDetail.getStage().getStageName() +"</td></tr>\n");
         strBuffer.append("          <tr><td>Release</td> <td>"+ regressDetail.getStage().getRelease().getName()+"</td></tr>\n");
         strBuffer.append("          <tr><td>ResultDirectory</td> <td>"+ regressDetail.getWorkLoc() +"</td></tr>\n");
         strBuffer.append("          <tr><td>FarmRunId</td><td>"+ regressDetail.getFarmrunId() +"</td></tr>\n");
         strBuffer.append("          <tr><td>StartTime</td><td>"+ regressDetail.getStarttime() +"</td></tr>\n");
         strBuffer.append("          <tr><td>StartTime</td><td>"+ regressDetail.getEndtime() + "</td></tr>\n");
         strBuffer.append("          <tr><td>SucCount</td><td>"+ regressDetail.getSucCount() + "</td></tr>\n");
         strBuffer.append("          <tr><td>DifCount</td><td>"+ regressDetail.getDifCount() + "</td></tr>\n");
         strBuffer.append("        </table>\n");
         strBuffer.append("    </BODY>\n");
         strBuffer.append("</HTML>\n");
      }
      catch (Exception e)
      {
         sr.print("Exception : Composing mail" + e);
      }
     // System.out.println(strBuffer.toString());
   }

  
}
