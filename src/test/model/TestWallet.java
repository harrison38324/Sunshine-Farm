package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestWallet {
    private Wallet wallet;

    @BeforeEach
    void runBefore(){
        wallet = new Wallet();
    }

    @Test
    void testGetBalance(){
        assertEquals(wallet.getBalance(), 0);
    }

    @Test
    void testEarn(){
        wallet.earn(10);
        assertEquals(wallet.getBalance(), 10);

        wallet.earn(20);
        assertEquals(wallet.getBalance(), 30);

        wallet.earn(0);
        assertEquals(wallet.getBalance(), 30);
    }

    @Test
    void testSpend(){
        wallet.earn(100);
        assertEquals(wallet.getBalance(), 100);
        wallet.spend(1);
        assertEquals(wallet.getBalance(), 99);
        wallet.spend(20);
        assertEquals(wallet.getBalance(), 79);
        wallet.spend(55);
        assertEquals(wallet.getBalance(), 24);
        wallet.spend(0);
        assertEquals(wallet.getBalance(), 24);
    }
}
