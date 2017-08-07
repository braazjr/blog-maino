package com.braazjr.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtil {

	@SuppressWarnings("unused")
	public static Connection getConnection() throws URISyntaxException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

		Connection connection = DriverManager.getConnection(dbUrl, username, password);

		Statement stmt = connection.createStatement();
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS post (codigo serial not null, titulo varchar not null, conteudo varchar not null, dataHora timestamp not null)");
		stmt.executeUpdate(
				"CREATE TABLE IF NOT EXISTS comentario (codigo serial not null, usuario varchar not null, conteudo varchar not null, dataHora timestamp not null, codigoPost int not null)");

		return connection;
	}
}
