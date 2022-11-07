import { Avatar, Button, CircularProgress, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useFBApi } from "../../../hooks";
import { Header, Post } from "../../components";
import "./perfil.style.css";

export function Perfil() {
  const [pessoa, setPessoa] = useState();
  const [posts, setPosts] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [carregando, setCarregando] = useState(false);
  const [usuarioLogado, setUsuarioLogado] = useState(null);
  const { email } = useParams();
  const FBApi = useFBApi();

  useEffect(() => {
    async function getApi() {
      setCarregando(true);

      const newPessoa = await FBApi.buscarPessoaEmail(email);

      setPessoa(newPessoa);

      const newPosts = await FBApi.feedPostsPerfil(pagina, email);

      setPosts([...posts, ...newPosts.content]);

      const novoUsuario = await FBApi.usuarioLogado();

      setUsuarioLogado(novoUsuario);

      setCarregando(false);
    }

    getApi();
  }, [pagina]);

  function handleCarregar() {
    setCarregando(true);
    setPagina(pagina + 1);
  }

  return (
    <div className="perfil">
      <Header />

      {pessoa ? (
        <div className="perfil__info">
          <Avatar
            alt="Remy Sharp"
            src={pessoa.imagemPerfil}
            sx={{ width: 150, height: 150 }}
          />
          <Typography variant="h5" component="p" className="card_usuario__nome">
            {pessoa.nome} {pessoa.sobrenome}
          </Typography>
          <Typography variant="h6" component="p" className="card_usuario__nome">
            {pessoa.apelido}
          </Typography>
        </div>
      ) : (
        <CircularProgress color="inherit" />
      )}

      <div className="perfil__feed">
        {posts.length && usuarioLogado !== null
          ? posts.map((post) => {
              return (
                <Post key={post.id} postProp={post} usuario={usuarioLogado} />
              );
            })
          : null}

        <div className="perfil__feed_carregando">
          {carregando ? (
            <CircularProgress color="inherit" />
          ) : (
            <Button fullWidth variant="contained" onClick={handleCarregar}>
              Carregar mais posts
            </Button>
          )}
        </div>
      </div>
    </div>
  );
}
