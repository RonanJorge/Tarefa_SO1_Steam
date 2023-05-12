package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import br.edu.fateczl.fila.filaobj.Fila;

public class SteamController implements ISteamController{
	
	public void ExibirSteam(String ano, String mes, float media, String path, String nome) throws IOException{
		File arq = new File(path, nome);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(linha.contains(ano) && linha.contains(mes)) {
					String[] vetor = linha.split(",");
					double jogadores = Double.parseDouble(vetor[3]);
					if(jogadores > media) {
						System.out.println("Nome do jogo: "+vetor[0]+" | Média de jogadores ativos: "+vetor[3]);
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}
		else {
			throw new IOException("Arquivo Inválido");
		}
	}
	
	public void GerarSteam(String ano, String mes, String path, String nome, String nome_arq) throws Exception {
		File dir = new File(path);
		File arq = new File(path, nome_arq + ".csv");
		if(dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if(arq.exists()) {
				existe = true;
			}
			String conteudo = geraTxt(ano, mes, path, nome);
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório Inválido");
		}
	}

	private String geraTxt(String ano, String mes, String path, String nome) throws Exception {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		Fila fila_2 = new Fila();
		fila_2 = ExtrairSteam(ano, mes, path, nome);
		while (!fila_2.isEmpty()) {
			linha = (String) fila_2.remove();
			buffer.append(linha+"\r\n");
		}
		return buffer.toString();
	}
	
	public Fila ExtrairSteam(String ano, String mes, String path, String nome) throws IOException{
		File arq = new File(path, nome);
		Fila fila = new Fila();
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			while(linha != null) {
				if(linha.contains(ano) && linha.contains(mes)) {
					String[] vetor = linha.split(",");
					String x = "Nome do jogo: "+vetor[0]+" | Média de jogadores ativos: "+vetor[3];
					fila.insert(x);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leitor.close();
			fluxo.close();
		}
		else {
			throw new IOException("Arquivo Inválido");
		}
		return fila;
	}
	
	
}





















