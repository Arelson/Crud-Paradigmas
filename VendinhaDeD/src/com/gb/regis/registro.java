package com.gb.regis;

import com.gb.servicoUsuario.ServicoUsuario;


public class registro {
	public static void main(String[] args) {
		//Aventureiro a = new Sentinela(1, "Arlison", 23, 120.45, 39, 700);
		//System.out.println(a.getNome());


		ServicoUsuario servidor = new ServicoUsuario();

        servidor.criaGuerreiro(0, "Mateus", 22, 400, 30, 40);
		servidor.criaSentinela(0, "Arlison", 23, 120.45, 39, 700);
		servidor.criaGuerreiro(0, "mateuzinho", 40, 500, 1, 100);

		servidor.listarAventureiro();
		servidor.deletarAventureiro(2);
		servidor.listarAventureiro();

		servidor.atualizarAventureiro(3, "lukinha");
		servidor.listarAventureiro();
		
		//System.out.println(servidor.getNomeUsuarioPorId(3));
	}
}
