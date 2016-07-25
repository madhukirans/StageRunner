package com.oracle.stagerun.tool;



import com.oracle.stagerun.entities.RegressDetails;
import java.io.FileInputStream;
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
      String to = "madhu.seelam@oracle.com";//StageRun.versionProperties.getProperty("GROUP_EMAIL");

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
         String subject = "Results for stage:" + regressDetail.getStageId().getStageName() + " Release: " + 
                 regressDetail.getStageId().getReleaseEntity().getReleaseName()
                 + regressDetail.getTestunitId().getTestUnitName();     
         
         String yourEncodedString = MimeUtility.encodeText(subject, "UTF-8", "B");

         //message.setSubject(yourEncodedString);
         message.setHeader("Subject", yourEncodedString);
         // Now set the actual message
         message.setText(strBuffer.toString(), "UTF-8", "html");

         // Send message
         Transport.send(message);
         StageRun.print("Sent message successfully....",regressDetail);
      }
      catch (Exception mex)
      {
         mex.printStackTrace();
      }
   }

   public void generateContent()
   {
      try
      {
         //int numberOfJobs = Integer.parseInt(prop.getProperty("numberOfJobs"));
         //int numberOfFarmJobs = Integer.parseInt(prop.getProperty("numberOfFarmJobs"));

         String caption = "Stage:" + regressDetail.getStageId().getStageName() + "<br>Release: " + regressDetail.getStageId().getReleaseEntity().getReleaseName();

         strBuffer.append("    <table>\n");
         strBuffer.append("       <CAPTION><b><i>" + caption + "</i><b></CAPTION>\n");         
         strBuffer.append("          <tr><td>Stage</td></tr> <td>"+ regressDetail.getStageId().getStageName() +"</td></tr>\n");
         strBuffer.append("          <tr><td>Release</td></tr> <td>"+ regressDetail.getStageId().getReleaseEntity().getReleaseName() +"</td></tr>\n");
         strBuffer.append("          <tr><td>ResultDirectory</td></tr> <td>"+ regressDetail.getWorkLoc() +"</td></tr>\n");
         strBuffer.append("          <tr><td>FarmRunId</td></tr> <td>"+ regressDetail.getFarmrunId() +"</td></tr>\n");
         strBuffer.append("          <tr><td>StartTime</td></tr> <td>"+ regressDetail.getStarttime() +"</td></tr>\n");
         strBuffer.append("          <tr><td>StartTime</td></tr> <td>"+ regressDetail.getEndtime() + "</td></tr>\n");
         strBuffer.append("        </table>\n");
         strBuffer.append("    </BODY>\n");
         strBuffer.append("</HTML>\n");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
     // System.out.println(strBuffer.toString());
   }

  
}
