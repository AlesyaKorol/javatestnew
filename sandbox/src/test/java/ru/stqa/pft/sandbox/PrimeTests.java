package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Alesia on 27.04.17.
 */
public class PrimeTests {

  @Test
  public void tetsPrime(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void tetsNonPrimes(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }

  @Test (enabled = false)
  public void tetsPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

}
