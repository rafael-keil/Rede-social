import { useMemo } from "react";
import { useGlobalUser } from "../../../context";
import { useHttp } from "../_base/use-http";

export function useFBApi() {
  const [user] = useGlobalUser();

  const httpInstance = useHttp("http://localhost:8090/tcc-java", {
    authorization: user,
  });

  async function cadastroUsuario(usuario) {
    const response = await httpInstance.post(
      "/publico/usuario/registrar",
      usuario
    );
    return response;
  }

  async function logarUsuario(usuario) {
    const response = await httpInstance.post("/publico/usuario/logar", usuario);
    return response;
  }

  async function feedPosts(page) {
    const response = await httpInstance.get(
      `/privado/post?page=${page}&size=5`
    );
    return response;
  }

  async function usuarioLogado() {
    const response = await httpInstance.get("/privado/usuario");
    return response;
  }

  async function criarPost(post) {
    const response = await httpInstance.post("/privado/post", post);
    return response;
  }

  async function criarComentario(comentario, id) {
    const response = await httpInstance.post(
      `/privado/comentar/${id}`,
      comentario
    );
    return response;
  }

  async function curtir(post) {
    const response = await httpInstance.post(`/privado/curtir/${post}`);
    return response;
  }

  async function buscarComentarios(id) {
    const response = await httpInstance.get(`/privado/comentar/${id}`);
    return response;
  }

  async function alterarPrivacidade(id) {
    const response = await httpInstance.put(`/privado/post/${id}`);
    return response;
  }

  async function listarAmigos(page) {
    const response = await httpInstance.get(
      `/privado/solicitacao?page=${page}&size=10`
    );
    return response;
  }

  async function pesquisarUsuario(pesquisa) {
    const response = await httpInstance.get(
      `/privado/usuario/pesquisar/${pesquisa}`
    );
    return response;
  }

  async function solicitarAmizade(email) {
    const response = await httpInstance.post(`/privado/solicitacao/${email}`);
    return response;
  }

  async function listarSolicitacao(page) {
    const response = await httpInstance.get(
      `/privado/solicitacao/pendente?page=${page}&size=10`
    );
    return response;
  }

  async function aceitarSolicitacao(id) {
    const response = await httpInstance.put(
      `/privado/solicitacao/aceitar/${id}`
    );
    return response;
  }

  async function deletarSolicitacao(id) {
    const response = await httpInstance.put(
      `/privado/solicitacao/deletar/${id}`
    );
    return response;
  }

  async function buscarPessoaEmail(email) {
    const response = await httpInstance.get(`/privado/usuario/${email}`);
    return response;
  }

  async function feedPostsPerfil(page, email) {
    const response = await httpInstance.get(
      `/privado/post/${email}?page=${page}&size=5`
    );
    return response;
  }

  return useMemo(
    () => ({
      cadastroUsuario,
      logarUsuario,
      feedPosts,
      usuarioLogado,
      criarPost,
      criarComentario,
      curtir,
      buscarComentarios,
      alterarPrivacidade,
      listarAmigos,
      pesquisarUsuario,
      solicitarAmizade,
      listarSolicitacao,
      deletarSolicitacao,
      aceitarSolicitacao,
      buscarPessoaEmail,
      feedPostsPerfil,
    }),
    []
  );
}
