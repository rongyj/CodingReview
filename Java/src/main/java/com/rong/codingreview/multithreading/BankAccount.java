package com.rong.codingreview.multithreading;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * I have the code shown below. The caller of the transferFunds() method is
 multithreaded and may call this method with the same account as either the
 source or the destination argument from different threads. For example, in
 thread 1, account #1 may be the source while in thread 2 it may be the
 destination of a transfer. Make the implementation of this method thread
 safe.. Feel free to rewrite any portion of the transferFunds() method
 necessary provided the result is the same. You can add new methods and
 classes as well.

 public class Account {

 private long id;
 private int availableFunds;

 // Getters and setters for the above fields.

 }

 public static void transferFunds(Account source, Account destination, int
 amount) {

 if (source.getAvailableFunds() >= amount) {
 source.setAvailableFunds(source.getAvailableFunds() - amount);
 destination.setAvailableFunds(destination.getAvailableFunds() + amount);
 } else {
 throw new IllegalArgumentException(“Insufficient funds in source: “ +
 source);
 }
 }
 */


public class BankAccount

{
    private long id;
    private AtomicInteger availableFunds;
    // Getters and setters for the above fields.
    public BankAccount(long id,int initialFund){
        this.id=id;
        availableFunds=new AtomicInteger(initialFund);
    }

    public AtomicInteger getAvailableFunds(){
        return availableFunds;
    }

    public String toString(){
        return new StringBuffer().append("The Account ").append(this.id).append(" has available funds $").append(this.availableFunds.get()).toString();
    }


    public static void transferFunds(BankAccount source, BankAccount destination, int
            amount) {

        boolean isSuccess=false;
        while(!isSuccess) {
            int availableFund = source.getAvailableFunds().getAndAdd(0);
            if (availableFund >= amount) {
                if (source.getAvailableFunds().compareAndSet(availableFund, availableFund - amount)) {  // This is to make sure the fund does not get changed
                    destination.getAvailableFunds().getAndAdd(amount);
                    isSuccess=true;
                }
            } else {
                throw new IllegalArgumentException("Insufficient funds in source;" + source);
            }
        }
    }


}
