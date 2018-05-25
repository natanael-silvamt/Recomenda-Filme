package br.com.quixada.ufc.recfilme.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import br.com.quixada.ufc.recfilme.dao.*;
import br.com.quixada.ufc.recfilme.pojo.*;

public class Main {

	private static Scanner entrada;
	private static Scanner entrada_int;

	public static void main(String[] args) throws ParseException {
		FilmeDAO filmeDAO = new FilmeDAO();
		UsuarioDAO userDAO = new UsuarioDAO();
		PreferenciaGeneroDAO prefGeneroDAO = new PreferenciaGeneroDAO();
		GeneroDAO generoDAO = new GeneroDAO();
		FilmeAssistidoDAO filmeAssistidoDAO = new FilmeAssistidoDAO();
		AtorDAO atorDAO = new AtorDAO();
		PreferenciaAtorDAO prefAtorDAO = new PreferenciaAtorDAO();
		RecomendaFilmeDAO recomendaDAO = new RecomendaFilmeDAO();
		
		int opcao;
		entrada = new Scanner(System.in);
		entrada_int = new Scanner(System.in);
		boolean fim = false;
		
		while(!fim) {
		//	String app_name = " ############  \n"
			//		        + " ##";
				  
				  
		//	System.out.println(app_name);
			//System.out.println("RecFlix");
			System.out.println("|  1 | Cadastrar filme.");
			System.out.println("|  2 | Cadastrar Usuario.");
			System.out.println("|  3 | Cadastrar Genero.");
			System.out.println("|  4 | Cadastrar Ator.");
			System.out.println("|  5 | Cadastrar Preferencia de Genero.");
			System.out.println("|  6 | Cadastrar Preferencia de Ator.");
			System.out.println("|  7 | Cadastrar Um Filme Como Assistido.");
			System.out.println("|  8 | ** Recomendar Filme. **");
			System.out.println("|  9 | Ver Filmes Assistidos.");
			System.out.println("| 10 | Ver Filmes Cadastrados.");
			System.out.println("| 11 | Ver Ususarios Cadastrados.");
			System.out.println("| 12 | Ver Generos Cadastrados.");
			System.out.println("| 13 | Ver Atores Cadastrados.");
			System.out.println("| 14 | Excluir filme.");
			System.out.println("| 15 | Excluir usuario.");
			System.out.println("| 16 | Excluir ator.");
			System.out.println("| 17 | Atualizar usuario.");
			System.out.println("|  0 | Sair.");
			
			opcao = entrada.nextInt();
			entrada.nextLine();
			
			switch(opcao) {
			case 1:{
				String nome, genero, ator_principal, ator_coadjuvante, duracao, nome_diretor, data_lancamento;
				System.out.println("Digite o nome do filme:");
				nome = entrada.nextLine();
				System.out.println("Digite o genero do filme:");
				genero = entrada.nextLine();
				System.out.println("Digite o nome do ator principal:");
				ator_principal = entrada.nextLine();
				System.out.println("Digite o nome do ator coadjuvante:");
				ator_coadjuvante = entrada.nextLine();
				System.out.println("Digite a duração do filme:");
				duracao = entrada.nextLine();
				System.out.println("Digite o nome do diretor do filme:");
				nome_diretor = entrada.nextLine();
				System.out.println("Digite a data(dia/mes/ano) de lançamento do filme:");
				data_lancamento = entrada.nextLine();
				
				DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				Date data = formato.parse(data_lancamento);

				Filme filme = new Filme(nome, genero, ator_principal, ator_coadjuvante, duracao, nome_diretor, data);
				if(filmeDAO.addFilme(filme)) {
					System.out.println("Filme cadastrado com sucesso!");
				}else {
					System.out.println("Error ao cadastrar filme !!");
				}			
				break;
			}case 2:{
				String nome, email;
				System.out.println("Digite o nome do usuario:");
				nome = entrada.nextLine();
				System.out.println("Digite o email do usuario:");
				email = entrada.nextLine();
				Usuario usuario = new Usuario(nome, email);
				if(userDAO.addUser(usuario)) {
					System.out.println("Usuario cadastrado com sucesso!");
				}else {
					System.out.println("Error ao cadastrar usuario !!");
				}
				break;
			} case 3:{
				String nome;
				System.out.println("Digite o nome do genero:");
				nome = entrada.nextLine();
				Genero genero = new Genero(nome);
				if(generoDAO.addGenero(genero))
					System.out.println("Genero cadastrado com sucesso!");
				else 
					System.out.println("Error ao cadastrar genero !!");
				break;
			} case 4:{
				String nome;
				System.out.println("Digite o nome do ator:");
				nome = entrada.nextLine();
				Ator ator = new Ator(nome);
				if(atorDAO.addAtor(ator))
					System.out.println("Ator cadastrado com sucesso!");
				else 
					System.out.println("Error ao cadastrar ator !!");
				break;
			}case 5:{
				int id_usuario, id_genero;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				System.out.println("Digite o ID do genero:");
				id_genero = entrada_int.nextInt();
				Usuario usuario = userDAO.getUserById(id_usuario);
				Genero genero = generoDAO.getGeneroById(id_genero);
				if(prefGeneroDAO.addPreferencia(usuario, genero))
					System.out.println("Preferencia cadastrada com sucesso!");
				else
					System.out.println("Error ao cadastrar preferencia !!");
				break;
			} case 6:{
				int id_usuario, id_ator;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				System.out.println("Digite o ID do Ator:");
				id_ator = entrada_int.nextInt();
				Usuario usuario = userDAO.getUserById(id_usuario);
				Ator ator = atorDAO.getAtorById(id_ator);
				if(prefAtorDAO.addPrefAtor(usuario, ator))
					System.out.println("Preferencia cadastrada com sucesso!");
				else
					System.out.println("Error ao cadastrar preferencia !!");				
				break;
			}case 7:{
				int id_usuario, id_filme;
				System.out.println("Digite o ID do usuario que assistiu o filme:");
				id_usuario = entrada_int.nextInt();
				System.out.println("Digite o ID do filme:");
				id_filme = entrada_int.nextInt();
				Usuario usuario = userDAO.getUserById(id_usuario);
				Filme filme = filmeDAO.getFilmeById(id_filme);
				if(filmeAssistidoDAO.addFilmeAssistido(usuario, filme))
					System.out.println("Filme cadastrado como assistido com sucesso!"); 
				else
					System.out.println("Error ao cadastrar filme !!");
				break;
			} case 8:{
				int id_usuario;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				Usuario usuario = userDAO.getUserById(id_usuario);
				ArrayList<Filme> listaFilmes = recomendaDAO.recomendaFilme(usuario);
				for(Filme filme : listaFilmes)
					System.out.println(filme.toString());			
				break;
			} case 9:{
				int id_usuario;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				ArrayList<Filme> lista = filmeAssistidoDAO.getFilmeAssistido(id_usuario);
				for(Filme filme : lista)
					System.out.println(filme.toString());
				break;
			} case 10:{
				ArrayList<Filme> filmes = filmeDAO.getFilmes();
				for(Filme filme : filmes)
					System.out.println(filme.toString());
				break;
			} case 11:{
				ArrayList<Usuario> usuarios = userDAO.getListUser();
				for(Usuario usuario : usuarios)
					System.out.println(usuario.toString());
				break;
			} case 12:{
				ArrayList<Genero> generos = generoDAO.getGenero();
				for(Genero genero : generos)
					System.out.println(genero.toString());
				break;
			} case 13:{
				ArrayList<Ator> atores = atorDAO.getListAtor();
				for(Ator ator : atores)
					System.out.println(ator.toString());
				break;
			} case 14:{
				int id_filme;
				System.out.println("Digite o ID do filme:");
				id_filme = entrada_int.nextInt();
				if(filmeDAO.deleteFilme(id_filme))
					System.out.println("Filme excluido com sucesso.");
				else
					System.out.println("Error na exclusão do filme !!!");
				break;
			} case 15:{
				int id_usuario;
				System.out.println("Digite o ID do usuario:");
				id_usuario = entrada_int.nextInt();
				if(userDAO.deleteUser(id_usuario))
					System.out.println("Usuario excluido com sucesso.");
				else
					System.out.println("Error na exclusão do usuario !!!");
				break;
			} case 16:{
				int id_ator;
				System.out.println("Digite o ID do autor:");
				id_ator = entrada_int.nextInt();
				if(atorDAO.deleteAtor(id_ator))
					System.out.println("Ator excluido com sucesso.");
				else
					System.out.println("Error na exclusão do ator !!!");
				break;
			} case 17:{
				int id_usuario_antigo;
				String nome, email;
				System.out.println("Digite o ID do usuario a ser editado:");
				id_usuario_antigo = entrada_int.nextInt();
				System.out.println("Digite o novo nome:");
				nome = entrada.nextLine();
				System.out.println("Digite o novo email:");
				email = entrada.nextLine();
				Usuario usuario = new Usuario(nome, email);
				if(userDAO.updateUsuario(id_usuario_antigo, usuario))
					System.out.println("Usuario editado com sucesso.");
				else
					System.out.println("Error na atualização do usuario !!!");
				break;
			} case 0:{
				System.exit(0);
				break;
			}
			default :
				fim = true;
				break;
			}		
		}
	}
}
