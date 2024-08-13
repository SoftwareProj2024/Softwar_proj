package ProductionCode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class ContentManagement {
	
	public String feedbackDeleteMessage;
	public String responseMessage;
	public String RecipeDeletedMessage;


	 public void viewRecipes() {
		    String filePath = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\recipes.txt";
		    BufferedReader reader = null;

		    try {
		        reader = new BufferedReader(new FileReader(filePath));
		        String line;
		        while ((line = reader.readLine()) != null) {
		            System.out.println(line);
		        }
		    } catch (IOException e) {
		        e.printStackTrace(); // Handle the exception appropriately in your application
		    } finally {
		        try {
		            if (reader != null) {
		                reader.close();
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}

	 public void deleteRecipes(String product) {
	     String filePath = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\recipes.txt";
	     
	     List<String> recipes = new ArrayList<>();
	     BufferedReader reader = null;
	     BufferedWriter writer = null;

	     try {
	         // قراءة جميع السطور من الملف وحفظها في الذاكرة باستثناء السطر الذي يحتوي على المنتج المراد حذفه
	         reader = new BufferedReader(new FileReader(filePath));
	         String line;
	         
	         while ((line = reader.readLine()) != null) {
	             // التحقق إذا كان السطر يبدأ باسم المنتج
	             if (!line.toLowerCase().startsWith(product.toLowerCase() + ":")) {
	                 recipes.add(line); // إضافة السطر إلى القائمة إذا لم يكن هو المطلوب حذفه
	             }
	         }
	         reader.close(); // إغلاق الملف بعد القراءة
	         
	         // كتابة المحتوى المعدل إلى نفس الملف بعد الحذف
	         writer = new BufferedWriter(new FileWriter(filePath));
	         for (String recipe : recipes) {
	             writer.write(recipe);
	             writer.newLine();
	             RecipeDeletedMessage="Recipe deleted successfully.";
	         }

	     } catch (IOException e) {
	         e.printStackTrace(); // التعامل مع الأخطاء بالشكل المناسب في التطبيق الخاص بك
	     } finally {
	         try {
	             if (writer != null) {
	                 writer.close(); // إغلاق الملف بعد الكتابة
	             }
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     }
	 }
	 

	 public void responseFeedback(String id, String responsMessage) {
	     String feedbackFilePath = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\feedback.txt";
	     String responseFilePath = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\responseFeedback.txt";
	     
	     BufferedReader reader = null;
	     BufferedWriter writer = null;
	     String username = null;

	     try {
	         // قراءة ملف الفيدباك للعثور على الـ id المطلوب والحصول على اسم المستخدم
	         reader = new BufferedReader(new FileReader(feedbackFilePath));
	         String line;
	         
	         while ((line = reader.readLine()) != null) {
	             // تقسيم السطر للحصول على id والـ username
	             String[] feedbackParts = line.split(":");
	             if (feedbackParts.length >= 3 && feedbackParts[0].equals(id)) {
	                 username = feedbackParts[1]; // الحصول على اسم المستخدم من الفيدباك
	                 break;
	             }
	         }
	         reader.close(); // إغلاق الملف بعد القراءة
	         
	         if (username != null) {
	             // تكوين رسالة الرد
	             String response = "Response to " + username + ": " + responsMessage;
	             
	             // كتابة الرسالة في ملف الردود
	             writer = new BufferedWriter(new FileWriter(responseFilePath, true)); // فتح الملف في وضع الإلحاق
	             writer.write(response);
	             writer.newLine();
	             responsMessage="response send  successfully.";
	         } else {
	             System.out.println("Feedback with ID " + id + " not found.");
	         }

	     } catch (IOException e) {
	         e.printStackTrace(); // التعامل مع الأخطاء بالشكل المناسب في التطبيق الخاص بك
	     } finally {
	         try {
	             if (writer != null) {
	                 writer.close(); // إغلاق الملف بعد الكتابة
	             }
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     }
	 }

	 public void viewFeedback() {
		    String filePath = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\feedback.txt";
		    BufferedReader reader = null;

		    try {
		        // فتح الملف للقراءة
		        reader = new BufferedReader(new FileReader(filePath));
		        String line;
		        
		        // قراءة وطباعة كل سطر من الملف
		        while ((line = reader.readLine()) != null) {
		            System.out.println(line);
		        }
		    } catch (IOException e) {
		        e.printStackTrace(); // التعامل مع الأخطاء بالشكل المناسب في التطبيق الخاص بك
		    } finally {
		        try {
		            if (reader != null) {
		                reader.close(); // إغلاق الملف بعد الانتهاء من القراءة
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	 
	 public void deleteFeedback(String id) {
		    String filePath = "C:\\Users\\THINKPAD\\git\\Softwar_proj\\alternativeSweet\\files\\feedback.txt";
		    List<String> feedbacks = new ArrayList<>();
		    BufferedReader reader = null;
		    BufferedWriter writer = null;

		    try {
		        // قراءة كل سطر من الملف وحفظ السطور التي لا تحتوي على الـ id المطلوب حذفه
		        reader = new BufferedReader(new FileReader(filePath));
		        String line;
		        
		        while ((line = reader.readLine()) != null) {
		            // التحقق مما إذا كان السطر يحتوي على الـ id المطلوب
		            String[] parts = line.split(":");
		            if (parts.length > 1 && !parts[0].equals(id)) {
		                feedbacks.add(line); // إضافة السطر إلى القائمة إذا لم يكن هو المطلوب حذفه
		            }
		        }
		        reader.close(); // إغلاق الملف بعد القراءة
		        
		        // كتابة السطور المعدلة إلى نفس الملف
		        writer = new BufferedWriter(new FileWriter(filePath));
		        for (String feedback : feedbacks) {
		            writer.write(feedback);
		            writer.newLine();
		            feedbackDeleteMessage="feedback deleted successfully.";
		        }
		        
		    } catch (IOException e) {
		        e.printStackTrace(); // التعامل مع الأخطاء بالشكل المناسب في التطبيق الخاص بك
		    } finally {
		        try {
		            if (writer != null) {
		                writer.close(); // إغلاق الملف بعد الكتابة
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
	
	
	
	 public void printMessages(String message) {
	        JOptionPane.showMessageDialog(null,message );

	 }

}
