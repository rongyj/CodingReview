import com.rong.codingreview.multithreading.BankAccount;

/**
 * Created by rongyj on 2/4/17.
 */
public class FundTransferThread extends Thread {
    BankAccount source;
    BankAccount target;
    int amount;
    public FundTransferThread (BankAccount source, BankAccount target, int amount){
        this.source=source;
        this.target=target;
        this.amount=amount;
    }


    @Override
    public void run(){
        BankAccount.transferFunds(source,target,amount);
    }
}
