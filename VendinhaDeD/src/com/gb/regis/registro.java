package com.gb.regis;

import com.gb.ususario.Aventureiro;
import com.gb.ususario.sentinela;

public class registro {
	public static void main(String[] args) {
		Aventureiro a = new sentinela(1, "Arlison", 23, 120.45, 39, 700);
		
		System.out.println(a.getNome());
	}
}
