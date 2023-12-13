package com.ecom.user;

/*
 * 	Zugriffsrechte abhängig von Benutzerrolle
 * 
 * 	Zugriff auf Daten nur wenn Rolle != DENIED
 */

public enum UserRole {
	ADMIN, USER, EMPLOYEE, DENIED
}
