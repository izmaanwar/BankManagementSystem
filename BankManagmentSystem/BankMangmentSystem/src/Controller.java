/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






/**
 *
 * @author Faisal
 */
public class Controller {
    Model m;
    Controller()
    {
        m=new Model();
    }
    public boolean transfer (Account sAccount , Account rAccount , double amount){
        boolean transfer = false;
        
        if(m.isExist(sAccount.getAccountNo())&&m.isExist(rAccount.getAccountNo()))
        {
            sAccount.setAccountType(m.checkAccountType(sAccount.getAccountNo()));
            rAccount.setAccountType(m.checkAccountType(rAccount.getAccountNo()));
            if(sAccount.getAccountType().equalsIgnoreCase("CURRENT") &&rAccount.getAccountType().equalsIgnoreCase("CURRENT") ){
                if (m.checkAccountBalance(sAccount.getAccountNo())- amount >= 0){
                    transfer = m.transfer(rAccount, rAccount, amount);
                }
            }
            return transfer;
        }
        else
        {
           return false;
        }
    }
    public boolean AccountOpen(Account ac,Person ps)
    {
        boolean flag=false;
        if(m.isExist(ac.getAccountNo()))
        {   
            return flag;
        }
        else
        {
            flag=m.InsertAccount(ac, ps);
            
            return flag;
        }
    }
    public boolean Deposit(Account ac,double amnt)
    {
        int i=0;
       if(m.isExist(ac.getAccountNo()))
       {
           
            i=m.Deposit(ac.getAccountNo(), amnt);
       }
       if(i==0)
       {
           return false;
       }
       return true; 
    }
    public boolean withdraw(Account ac,double amnt)
    {
       if(m.isExist(ac.getAccountNo()))
       {
           
           double d=m.checkAccountBalance(ac.getAccountNo());
          
           if(amnt <=d)
           {
               if(amnt>=100000)
               {
                    int b=(int)(amnt/100000);
                d=d*0.01;   
                d=d*b;
                   m.withdraw(ac.getAccountNo(), amnt,d);
                   
               }
               else
               {
                   m.withdraw(ac.getAccountNo(), amnt,0);
               }
               return true;
           }
           else
               return false;
       }
       return false;
       
    }
    public double checkBalance(Account ac)
    {
        if(m.isExist(ac.getAccountNo()))
        {
            String type=m.checkAccountType(ac.getAccountNo());
           if(type.equalsIgnoreCase("Savings"))
           {
               m.Updateby10P(ac);
           }
           return m.checkAccountBalance(ac.getAccountNo());
        }
        return -1;
    }
    public void showAll()
    {
       m.showAll(); 
    }

}
