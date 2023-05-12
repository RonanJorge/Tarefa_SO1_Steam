package controller;

import java.io.IOException;

public interface ISteamController {
	public void ExibirSteam(String ano, String mes, float media, String path, String nome ) throws IOException;
	public void GerarSteam(String ano, String mes, String path, String nome, String nome_arq) throws Exception;
}
