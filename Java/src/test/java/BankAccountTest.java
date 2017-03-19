import com.rong.codingreview.multithreading.BankAccount;
import com.rong.codingreview.multithreading.BankAccountInt;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by rongyj on 2/3/17.
 */

public class BankAccountTest extends TestCase{

    @Test
    public void testBankAccount(){
        final BankAccount account1=new BankAccount(1,1000);
        BankAccount account2=new BankAccount(2,500);


        Thread t1=new FundTransferThread(account1,account2,300);
        Thread t2=new FundTransferThread(account2,account1,200);
        Thread t3=new FundTransferThread(account1,account2,300);
        Thread t4=new FundTransferThread(account2,account1,600);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive()){
            try {
                Thread.sleep(2000);
            }catch (InterruptedException iex){
                iex.printStackTrace();
            }
        }
        assertEquals(100, account1.getAvailableFunds().get());
        assertEquals(1400,account2.getAvailableFunds().get());

        //
        //t4.start();
    }

    @Test
    public void testBankAccountInt(){
        BankAccountInt account1=new BankAccountInt(1,1000);
        BankAccountInt account2=new BankAccountInt(2,500);

        Thread t1=new FundTransferThreadInt(account1,account2,300);
        Thread t2=new FundTransferThreadInt(account2,account1,200);
        Thread t3=new FundTransferThreadInt(account1,account2,300);
        Thread t4=new FundTransferThreadInt(account2,account1,600);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive()){
            try {
                Thread.sleep(2000);
            }catch (InterruptedException iex){
                iex.printStackTrace();
            }
        }
        assertEquals(1200, account1.getAvailableFunds());
        assertEquals(300,account2.getAvailableFunds());

        //
        //t4.start();
    }
}
