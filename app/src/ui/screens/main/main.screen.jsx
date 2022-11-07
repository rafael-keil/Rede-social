import { Alert, Button, CircularProgress, Snackbar } from "@mui/material";
import { useEffect, useState } from "react";
import { useFBApi } from "../../../hooks";
import { AmigosList, CriarPost, NotificacaoList } from "../../components";
import { Header } from "../../components/header/header.component";
import { Post } from "../../components/post/post.component";
import "./main.style.css";

export function Main() {
  const [posts, setPosts] = useState([]);
  const [atualize, setAtualize] = useState(true);
  const [valido, setValido] = useState(true);
  const [pagina, setPagina] = useState(0);
  const [carregando, setCarregando] = useState(true);
  const [usuarioLogado, setUsuarioLogado] = useState(null);
  const FBApi = useFBApi();

  useEffect(() => {
    async function getApi() {
      const newPosts = await FBApi.feedPosts(pagina);

      setPosts([...posts, ...newPosts.content]);

      const novoUsuario = await FBApi.usuarioLogado();

      setUsuarioLogado(novoUsuario);

      setCarregando(false);
    }

    getApi();
  }, [pagina, atualize]);

  function handleCarregar() {
    setCarregando(true);
    setPagina(pagina + 1);
  }

  function handleClick() {
    setPosts([]);
    setPagina(0);

    setAtualize(!atualize);
    setValido(true);
  }

  function handleCloseValido() {
    setValido(!valido);
  }

  return (
    <div className="main">
      <Header />

      <div className="main__notificacoes">
        <NotificacaoList />
      </div>

      <div className="main__feed">
        <CriarPost onClick={handleClick} />

        {posts.length && usuarioLogado !== null
          ? posts.map((post) => {
              return (
                <Post key={post.id} postProp={post} usuario={usuarioLogado} />
              );
            })
          : null}

        <div className="main__feed_carregando">
          {carregando ? (
            <CircularProgress color="inherit" />
          ) : (
            <Button fullWidth variant="contained" onClick={handleCarregar}>
              Carregar mais posts
            </Button>
          )}
        </div>
      </div>

      <div className="main__amigos">
        <AmigosList />
      </div>

      <Snackbar
        open={valido}
        autoHideDuration={2000}
        onClose={handleCloseValido}
      >
        <Alert onClose={handleCloseValido} severity="success">
          Post criado
        </Alert>
      </Snackbar>
    </div>
  );
}
