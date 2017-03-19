
import com.rong.codingreview.multithreading.BankAccountInt;

/**
 * Created by rongyj on 2/4/17.
 */
public class FundTransferThreadInt extends Thread {
    BankAccountInt source;
    BankAccountInt target;
    int amount;

    public FundTransferThreadInt(BankAccountInt source, BankAccountInt target, int amount){
        this.source=source;
        this.target=target;
        this.amount=amount;
    }

    @Override
    public void run(){
        BankAccountInt.transferFunds(source,target,amount);
    }
}
