package com.braazjr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.braazjr.model.Post;
import com.braazjr.util.DAOUtil;

public class PostDAO {

	public ArrayList<Post> listarTodos() {
		try (Connection connection = DAOUtil.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM post");

			ArrayList<Post> output = new ArrayList<Post>();
			while (rs.next()) {
				output.add(new Post(rs.getLong("codigo"), rs.getString("titulo"), rs.getString("conteudo"),
						rs.getDate("dataHora")));
			}

			return output;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void salvar(Post post) {
		String SQL = "INSERT INTO post (titulo, conteudo, dataHora) VALUES (?, ?, now())";
		try {
			PreparedStatement statement = DAOUtil.getConnection().prepareStatement(SQL);
			statement.setString(1, post.getTitulo());
			statement.setString(2, post.getConteudo());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Post buscarPorCodigo(Long codigo) {
		String SQL = "SELECT * FROM post WHERE codigo = ?";
		try {
			PreparedStatement statement = DAOUtil.getConnection().prepareStatement(SQL);
			statement.setLong(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Post(rs.getLong("codigo"), rs.getString("titulo"), rs.getString("conteudo"),
						rs.getDate("dataHora"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void excluir(Long codigo) {
		String SQL = "DELETE FROM post WHERE codigo = ?";
		try {
			PreparedStatement statement = DAOUtil.getConnection().prepareStatement(SQL);
			statement.setLong(1, codigo);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
