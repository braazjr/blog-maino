package com.braazjr.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.braazjr.dao.ComentarioDAO;
import com.braazjr.dao.PostDAO;
import com.braazjr.model.Comentario;
import com.braazjr.model.Post;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping
	public ModelAndView index() {
		PostDAO dao = new PostDAO();

		ModelAndView view = new ModelAndView("index");
		view.addObject("posts", dao.listarTodos());

		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvarPost(Post post) {
		PostDAO dao = new PostDAO();
		dao.salvar(post);

		return index();
	}

	@RequestMapping("/post/{codigo}")
	public ModelAndView getPostDetail(@PathVariable(value = "codigo") Long codigo) {
		PostDAO dao = new PostDAO();
		Post post = dao.buscarPorCodigo(codigo);

		ComentarioDAO comentarioDAO = new ComentarioDAO();

		ArrayList<Comentario> comentarios = new ArrayList<>();
		int quantidadeComentarios = 0;

		if (comentarioDAO.buscarPorCodigoPost(codigo) != null) {
			quantidadeComentarios = comentarioDAO.buscarPorCodigoPost(post.getCodigo()).size();
			comentarios = comentarioDAO.buscarPorCodigoPost(post.getCodigo());
		}

		ModelAndView view = new ModelAndView("post");
		view.addObject("post", post);
		view.addObject("quantidadeComentario", quantidadeComentarios);
		view.addObject("comentarios", comentarios);

		return view;
	}

	@RequestMapping(value = "/post/{codigo}", method = RequestMethod.POST)
	public String salvarComentario(Comentario comentario) {
		ComentarioDAO dao = new ComentarioDAO();
		dao.salvar(comentario);

		return "redirect:/post/" + comentario.getCodigoPost();
	}

	@RequestMapping(value = "/post/{codigo}", method = RequestMethod.DELETE)
	public String excluirPost(@PathVariable(value = "codigo") Long codigo) {
		PostDAO dao = new PostDAO();
		dao.excluir(codigo);

		return "redirect:/";
	}
}
