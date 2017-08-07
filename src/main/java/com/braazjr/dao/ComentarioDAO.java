package com.braazjr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.braazjr.model.Comentario;
import com.braazjr.util.DAOUtil;

public class ComentarioDAO {

	public ArrayList<Comentario> listarTodos() {
		try (Connection connection = DAOUtil.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM comentario");

			ArrayList<Comentario> output = new ArrayList<Comentario>();
			while (rs.next()) {
				output.add(new Comentario(rs.getLong("codigo"), rs.getString("usuario"), rs.getString("conteudo"),
						rs.getDate("datahora"), rs.getLong("codigopost")));
			}

			return output;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public void salvar(Comentario comentario) {
		String SQL = "INSERT INTO comentario (usuario, conteudo, datahora, codigopost) VALUES (?, ?, now(), ?)";
		try {
			PreparedStatement statement = DAOUtil.getConnection().prepareStatement(SQL);
			statement.setString(1, comentario.getUsuario());
			statement.setString(2, comentario.getConteudo());
			statement.setLong(3, comentario.getCodigoPost());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Comentario buscarPorCodigo(Long codigo) {
		String SQL = "SELECT * FROM comentario WHERE codigo = ?";
		try {
			PreparedStatement statement = DAOUtil.getConnection().prepareStatement(SQL);
			statement.setLong(1, codigo);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Comentario(rs.getLong("codigo"), rs.getString("titulo"), rs.getString("conteudo"),
						rs.getDate("datahora"), rs.getLong("codigopost"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Comentario> buscarPorCodigoPost(Long codigo) {
		String SQL = "SELECT * FROM comentario WHERE codigoPost = ?";
		try {
			PreparedStatement statement = DAOUtil.getConnection().prepareStatement(SQL);
			statement.setLong(1, codigo);
			ResultSet rs = statement.executeQuery();

			ArrayList<Comentario> output = new ArrayList<Comentario>();
			while (rs.next()) {
				output.add(new Comentario(rs.getLong("codigo"), rs.getString("usuario"), rs.getString("conteudo"),
						rs.getDate("dataHora"), rs.getLong("codigoPost")));
			}

			return output;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
