package com.gb.regis;

import com.gb.usuario.Aventureiro;
import com.gb.usuario.Sentinela; 

public class registro {
	public static void main(String[] args) {
		Aventureiro a = new Sentinela(1, "Arlison", 23, 120.45, 39, 700);
		System.out.println(a.getNome());
	}
}
