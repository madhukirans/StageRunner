package com.oracle.stagerun.tool;



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
   Properties prop;
   private int numberOfJobs;
   public Mail(Properties prop)
   {
      this.prop = prop;
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
         String subject = "Results for stage:" + prop.getProperty("stage") + " Release: " + prop.getProperty("release") +
               " Number of jobs:" + numberOfJobs + "";         
         
         String yourEncodedString = MimeUtility.encodeText(subject, "UTF-8", "B");

         //message.setSubject(yourEncodedString);
         message.setHeader("Subject", yourEncodedString);
         // Now set the actual message
         message.setText(strBuffer.toString(), "UTF-8", "html");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
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

         String caption = "Stage:" + prop.getProperty("stage") + "<br>Release: " + prop.getProperty("release") +
               "<br>Farm Job IDs:";
//         for (FarmJob job: FarmJob.values())
//         {
//            if (job.isEnabled())
//               caption += prop.getProperty(job + ".farmid") + " ";
//         }

         strBuffer.append("        <table>\n");
         strBuffer.append("            <CAPTION><b><i>" + caption + "</i><b></CAPTION>\n");
         strBuffer.append("            <thead>\n");
         strBuffer.append("                <tr><td>S.No</td><td>Suite</td> <td>WorkLocation</td> <td>SucCount</td>" +
               " <td>DifCount</td> <td>UploadStatus</td> <td>Diffs</td></TR>\n");
         strBuffer.append("            </thead>\n");
         strBuffer.append("            <tbody>\n");
         
         int j =0;
//         for (FarmJob job: FarmJob.values())
//         {
//            if (!job.isEnabled())
//               continue;
//            
//            strBuffer.append("<tr>\n");
//            strBuffer.append("\t<td id=\"id1\">" + ++j + "</td>\n");
//            String str = prop.getProperty(job + ".dir") + "/";
//            strBuffer.append("\t<td id=\"id2\">" + job.toString() + "</td>\n");
//            strBuffer.append("\t<td id=\"id2\">" + str + "</td>\n");
//            strBuffer.append("\t<td id=\"id1\">" + prop.getProperty(job + ".succount") + "</td>\n");
//            strBuffer.append("\t<td id=\"id1\">" + prop.getProperty(job + ".difcount") + "</td>\n");
//
//            String uploadingStatus = prop.getProperty(job + ".uploadStatus");
//            if (uploadingStatus==null || uploadingStatus.equals("false"))
//               uploadingStatus = "<font color=\"red\"><h1>false</h1></font>";
//            
//            strBuffer.append("\t<td id=\"id1\">" + uploadingStatus + "</td>\n");
//            String diffs = prop.getProperty(job + ".difs");
//            
//            if (diffs != null)
//               diffs =  diffs.replaceAll(",", "\n").replaceAll(str, "");
//            
//            strBuffer.append("\t<td id=\"id2\"><textarea style=\"color:red; width: 100%; height: 100%; border: none\" >" + diffs + "</textarea></td>\n");
//            strBuffer.append("</tr>\n");
//         }
         
         numberOfJobs = j;

         strBuffer.append("            </tbody>\n");
         strBuffer.append("            <tfoot>\n");

//         for (FarmJob job: FarmJob.values())
//         {
//            if (!job.isEnabled())
//               continue;
//            strBuffer.append("            <tr>\n");
//            strBuffer.append("<td>\n");
//            strBuffer.append(prop.getProperty(job + ".farmid"));
//            strBuffer.append("</td>\n");
//            strBuffer.append("<td  colspan=5>\n");
//            strBuffer.append(prop.getProperty(job + ".farmcommand"));
//            strBuffer.append("</td>\n");
//            strBuffer.append("            </tr>\n");
//         }

         strBuffer.append("            </tfoot>\n");
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

   public static void main(String a[]) throws Exception
   {
      Properties prop = new Properties();

      FileInputStream fin = new FileInputStream(a[0]);
      prop.load(fin);
      Mail mail = new Mail(prop);      
      mail.sendMail();
   }
}
