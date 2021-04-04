package com.luv2code.springdemo.aspect;

import java.util.List;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.springdemo.Account;

@Aspect
@Component
@Order(3)
public class MyLoggingDemo {
	

	
	// run after throwing advice
	@AfterThrowing(pointcut = "execution(* com.luv2code.springdemo.dao.AccountDao.findAccounts(..))",
			throwing = "theExc")
	public void afterThrowungException(JoinPoint theJoinPoint , Throwable theExc) {
		
		// create method signature
		String method = theJoinPoint.getSignature().toShortString();
		
		System.out.println("\n ======> Executing afterReturning on the method " + method);
		
		// throwing the exception
		System.out.println("handling Exception: " + theExc);
		
	}
	
	@AfterReturning(
			pointcut = "execution(* com.luv2code.springdemo.dao.AccountDao.findAccounts(..))" ,
			returning ="accounts")
	public void afterCalledSpringDemo(JoinPoint thJoinPoint , List<Account> accounts) {
		
		// create method signature
		String methodSignature =  thJoinPoint.getSignature().toShortString();
		
		System.out.println("\n ======> Executing afterReturning on the method " + methodSignature);
		
		
		System.out.println("the list of account are: " + accounts);
		
		// convert names of accounts to be uppercase.
		convertToBeUpperCase(accounts);
		
	}

	private void convertToBeUpperCase(List<Account> accounts) {
		// loop through all accounts
		
		for(Account tempAccount: accounts) {
			String nameAccount = tempAccount.getName().toUpperCase();
			
			tempAccount.setName(nameAccount);
		}
		
	}
}
