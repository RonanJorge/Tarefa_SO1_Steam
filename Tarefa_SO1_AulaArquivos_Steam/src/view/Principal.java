package view;

import java.io.IOException;
import controller.ISteamController;
import controller.SteamController;

public class Principal {

	public static void main(String[] args) {
		ISteamController steam = new SteamController();
		String ano = "2020";
		String mes = "January";
		float media = 60;
		String path = "C:\\Users\\FATEC ZONA LESTE\\Downloads\\Exercicio_Arquivos_Steam";
		String nome = "SteamCharts.csv";
		String nome_arq = "Steam Filtrada";
		try {
			steam.ExibirSteam(ano, mes, media, path, nome);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			steam.GerarSteam(ano, mes, path, nome, nome_arq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
