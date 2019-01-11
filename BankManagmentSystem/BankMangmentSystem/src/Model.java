/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Faisal
 */
public class Model {
     private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_User = "System";
    private static final String DB_PWD = "abc";
	//private static final String UPDATE_CITY_BY_NAME = "update "
	
    private Connection openConnection(){
             Connection conn = null;

            try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn = DriverManager.getConnection(DB_URL, DB_User, DB_PWD);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }finally{
              return conn;
            }
    }
    public boolean isExist(String AccountNo)
    {
      boolean exist=false;
       Connection con = openConnection();
       PreparedStatement st = null;
       ResultSet rs = null;
       
       String query = "Select * from Account where AccountNo =? ";
       try{
           st = con.prepareStatement(query);
           st.setString(1,AccountNo );
           rs = st.executeQuery();
           if(rs.next())
           {
               exist=true;
           }
       
       }catch(SQLException sqlEx){
       
       
       }finally{
           try {
               rs.close();
               st.close();
              con.close(); 
            
           } catch (SQLException ex) {
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
           }
           return exist;
        }   
      
    }
    public void Updateby10P (Account ac)
    {
        Connection con=openConnection();
        DateFormat df=new SimpleDateFormat("DD-MM-YYYY");
        Date dt=new Date();
        String date=df.format(dt);
        int n=0;
        ResultSet rs=null;
        String qry="Select daate from Account where AccountNo=?";
        PreparedStatement pst=null;
        try {
             pst=con.prepareStatement(qry);
             pst.setString(1, ac.getAccountNo());
             rs=pst.executeQuery();
             rs.next();
             String dbDate=rs.getString(1);
             StringTokenizer st=new StringTokenizer(date,"-");
             st.nextToken();
             st.nextToken();
             int years1=Integer.parseInt(st.nextToken());
             st=new StringTokenizer(dbDate,"-");
             st.nextToken();
             st.nextToken();
             int years2=Integer.parseInt(st.nextToken());
             int diff=years1-years2;
             System.out.println(diff+" ");
             if(diff>=1)
             {
                double amnt=checkAccountBalance(ac.getAccountNo());
                amnt=(amnt*0.10*diff);
                Deposit(ac.getAccountNo(), amnt);
                qry="update Account set daate=? where AccountNo=?";
                pst=con.prepareStatement(qry);
                pst.setString(1, date);
                pst.setString(2, ac.getAccountNo());
                pst.execute();
                Deposit(ac.getAccountNo(), amnt);
             }
         } catch (SQLException ex) {
             Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
         }
        finally{
            try {
                rs.close();
                con.close();
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public String checkAccountType (String AccountNo){
       String AccountType = "NA";
       
       Connection con = openConnection();
       PreparedStatement st = null;
       ResultSet rs = null;
       
       String query = "Select AccType from Account where AccountNo =? ";
       try{
           st = con.prepareStatement(query);
           st.setString(1,AccountNo );
           rs = st.executeQuery();
           rs.next();
           AccountType =rs.getString(1);
       }catch(SQLException sqlEx){
       
       
       }finally{
           try {
               rs.close();
               st.close();
              con.close(); 
            
           } catch (SQLException ex) {
               Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
           }
           return AccountType;
        }   
            
       
       }
    
    public int withdraw(String Acno,double Amnt,double tax)
    {
        double d=checkAccountBalance(Acno);
        d-=Amnt;
        d-=tax;
        int flag=0;
            Connection con = openConnection();
            PreparedStatement st = null;
            String query = "Update Account set AccBalance=? where AccountNo=?";
            try{
                st = con.prepareStatement(query);
                st.setDouble(1, d);
                st.setString(2,Acno);
                flag=st.executeUpdate();

            }catch(SQLException sqlEx){
              sqlEx.printStackTrace();

            }finally{
                 try {
                     st.close();
                    con.close(); 

                 } catch (SQLException ex) {
                     Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 return flag;
             }
    }
    
    public int Deposit(String Acno,double Amnt)
    {
        double d=checkAccountBalance(Acno);
        d+=Amnt;
        int flag=0;
            Connection con = openConnection();
            PreparedStatement st = null;
            String query = "Update Account set AccBalance=? where AccountNo=?";
            try{
                st = con.prepareStatement(query);
                st.setDouble(1, d);
                st.setString(2,Acno);
                flag=st.executeUpdate();

            }catch(SQLException sqlEx){
              sqlEx.printStackTrace();

            }finally{
                 try {
                     st.close();
                    con.close(); 

                 } catch (SQLException ex) {
                     Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 return flag;
             }
    }
    
    public double checkAccountBalance (String AccountNo){
            double AccountBalance = -1;
            Connection con = openConnection();
            PreparedStatement st = null;
            ResultSet rs = null;
            String query = "Select AccBalance from Account where AccountNo =? ";
            try{
                st = con.prepareStatement(query);
                st.setString(1,AccountNo );
                rs = st.executeQuery();
                rs.next();
                AccountBalance =rs.getDouble(1);


            }catch(SQLException sqlEx){
              sqlEx.printStackTrace();

            }finally{
                 try {
                     rs.close();
                     st.close();
                    con.close(); 

                 } catch (SQLException ex) {
                     Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 return AccountBalance;
             }
       
       }
       
       public boolean transfer (Account SAccount , Account RAccount , double Amount){
           boolean transfer = false;
           Connection con = openConnection();
           PreparedStatement pst1 = null;
           PreparedStatement pst2  = null;
           PreparedStatement pst3  = null;
           String query = "update Account set AccBalance =? where Accountno =?";
           String query2 = "Insert into Transaction (TransId , TransAmount , TransTypeCode , TransDebitAccountNo , TransCreditAccountNo )"
                   + "values (Trans_Seq.nextval,?,?,?,?)";
           double senderOldBalance = checkAccountBalance (SAccount.getAccountNo());
           double receiverOldBalance =  checkAccountBalance (RAccount.getAccountNo());   
           double senderNewBalance = senderOldBalance-Amount;
           double receiverNewBalance = receiverOldBalance-Amount; int p1,  p2 , p3 =0;
           try{
               con.setAutoCommit(false);
               pst1 = con.prepareStatement(query);
               pst2 = con.prepareStatement(query);
               pst3 = con.prepareStatement(query2);
               pst1.setDouble(1,senderNewBalance);
               pst1.setString(2,SAccount.getAccountNo());
               pst2.setDouble(1,receiverNewBalance);
               pst2.setString(2,RAccount.getAccountNo());
               pst3.setDouble(1, Amount);
               pst3.setString(2,"Transfer");
               pst3.setString(3, SAccount.getAccountNo());
               pst3.setString(4, RAccount.getAccountNo());
               p1= pst1.executeUpdate();
               p2 = pst2.executeUpdate();
               p3 = pst3.executeUpdate();
               con.commit();
               con.setAutoCommit(true);
               transfer = true;
               
    
           
           
           }catch(SQLException sqle){
               con.rollback();
               con.setAutoCommit(true);
               sqle.printStackTrace();
                  
           }finally{
             
              return transfer;
           }
           
       }
       
       public static void main (String args[]){
       
//            Account s = new Account();
//            Account r = new Account ();
//            s.setAccountNo("AC001");
//            r.setAccountNo("AC002");
//            AccountModel m = new AccountModel();
//            boolean status = m.transfer(r, r, 1000);
//            System.out.println(status);
            
       
        }

    public boolean InsertAccount(Account ac,Person ps) {
        Connection con = openConnection();
            PreparedStatement st = null;
            ResultSet rs = null;
            String query = "Insert into Person values(?,?,?,?,?,?,?,?,?) ";
            try{
                con.setAutoCommit(false);
                st = con.prepareStatement(query);
                st.setString(1,ps.getName() );
                st.setString(2,ps.getFName() );
                st.setString(3,ps.getCNIC() );
                st.setString(4,ps.getGender() );
                st.setString(5,ps.getOccupation());
                st.setString(6,ps.getQualification());
                st.setString(7,ps.getAddress());
                st.setString(8,ps.getContact());
                st.setDouble(9,ps.getSalary() );
                st.execute();
                query = "Insert into Account values(?,?,?,?,?,?) ";
                st = con.prepareStatement(query);
                 st.setString(1,ac.getID() );
                 st.setString(2, ac.getAccountNo());
                st.setString(3,ac.getAccountTitle() );
                st.setString(4,ac.getAccountType());
                st.setDouble(5,ac.getAccountBalance() );
                st.setString(6,ac.getDate());
                st.execute();
                con.commit();
                con.setAutoCommit(true);


            }catch(SQLException sqlEx){
                con.rollback();
              sqlEx.printStackTrace();

            }finally{
                 try {
               
                     st.close();
                    con.close(); 

                 } catch (SQLException ex) {
                     Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 return true;
             }
       
    }

    public void showAll() {
            Connection con = openConnection();
            PreparedStatement st = null;
            ResultSet rs = null;
            String query = "Select * from Account";
            try{
                st = con.prepareStatement(query);
                rs = st.executeQuery();
                
                String cn=null;
                while(rs.next())
                {
                    cn=cn+rs.getString(2)+",";
                    cn=cn+rs.getString(3)+",";
                    cn=cn+rs.getDouble(5)+"\n";
                }
                JOptionPane.showMessageDialog(null,  cn,"All Accounts", JOptionPane.PLAIN_MESSAGE);
            }catch(SQLException sqlEx){
            sqlEx.printStackTrace();
            }finally{
                 try {
                     rs.close();
                     st.close();
                    con.close(); 

                 } catch (SQLException ex) {
                     Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }

    }

       
       
       

    
}
