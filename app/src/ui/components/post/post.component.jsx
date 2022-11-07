import {
  Button,
  Typography,
  ButtonGroup,
  CircularProgress,
  Switch,
  Snackbar,
  Alert,
} from "@mui/material";
import { useEffect, useState } from "react";
import { CardUsuario, Comentar, Comentario } from "..";
import { useFBApi } from "../../../hooks";
import "./post.style.css";

export function Post({ postProp, usuario }) {
  const [curtido, setCurtido] = useState(false);
  const [comentario, setComentario] = useState(false);
  const [comentarioList, setComentarioList] = useState([]);
  const [curtidaExtra, setCurtidaExtra] = useState(0);
  const [carregando, setCarregando] = useState(false);
  const [valido, setValido] = useState(false);
  const [post, setPost] = useState(postProp);
  const [dono, setDono] = useState(false);
  const FBApi = useFBApi();

  useEffect(() => {
    const newCurtido = post.curtida.some(
      (curtidaItem) => curtidaItem.autor.email === usuario?.email
    );

    setCurtido(newCurtido);

    if (post.autor.email === usuario.email) {
      setDono(true);
    }
  }, []);

  function handleCurtir() {
    curtido
      ? setCurtidaExtra(curtidaExtra - 1)
      : setCurtidaExtra(curtidaExtra + 1);

    setCurtido(!curtido);

    try {
      FBApi.curtir(post.id);
    } catch {
      setCurtido(!curtido);
    }
  }

  async function handleComentario() {
    setComentario(!comentario);

    setCarregando(true);

    if (!comentarioList.length) {
      const novosComentario = await FBApi.buscarComentarios(post.id);

      setComentarioList(novosComentario);
    }

    setCarregando(false);
  }

  function handleClick() {
    setComentarioList([]);

    setValido(true);
    handleComentario();
  }

  function handleChangeCheck() {
    const newPost = {
      ...post,
      isPrivado: !post.isPrivado,
    };

    setPost(newPost);

    FBApi.alterarPrivacidade(post.id);
  }

  function handleCloseValido() {
    setValido(!valido);
  }

  if (post != null) {
    return (
      <div className="post">
        <div className="post__header">
          <CardUsuario
            foto={post.autor.imagemPerfil}
            nome={post.autor.nome}
            email={post.autor.email}
          />

          {dono ? (
            <div className="post__header_privado">
              <Typography variant="body1" component="p">
                Privado
              </Typography>
              <Switch onChange={handleChangeCheck} checked={post.isPrivado} />
            </div>
          ) : null}

          <Typography variant="body1" component="p">
            {`${post.data[1]}/${post.data[2]}/${post.data[0]} ${post.data[3]}:${post.data[4]}:${post.data[5]}`}
          </Typography>
        </div>

        <div className="post__titulo">
          <Typography variant="h4" component="p">
            {post.titulo}
          </Typography>
          {post.valor ? (
            <Typography variant="h4" component="p">
              {`Valor: R$${post.valor}`}
            </Typography>
          ) : null}
        </div>

        <div className="post__corpo">
          {post.imagem ? (
            <img
              className="post__imagem"
              src={post.imagem}
              alt="imagem do post"
            />
          ) : null}

          <Typography variant="h5" component="p" className="post_descricao">
            {post.descricao}
          </Typography>
        </div>

        <div className="post__interacao">
          <div className="post__contagem">
            <Typography
              variant="body1"
              component="p"
              className="post__descricao"
            >
              Curtidas: {post.curtida?.length + curtidaExtra}
            </Typography>
          </div>

          <ButtonGroup fullWidth variant="outlined">
            {curtido ? (
              <Button onClick={handleCurtir} variant="contained">
                Descurtir
              </Button>
            ) : (
              <Button onClick={handleCurtir}>Curtir</Button>
            )}
            {comentario ? (
              <Button onClick={handleComentario} variant="contained">
                Fechar Comentarios
              </Button>
            ) : (
              <Button onClick={handleComentario}>Abrir Comentarios</Button>
            )}
          </ButtonGroup>
        </div>

        {carregando ? (
          <div className="post__loader">
            <CircularProgress color="inherit" />
          </div>
        ) : comentario ? (
          <div className="post__comentario">
            {comentarioList.map((comentarioItem) => {
              return (
                <Comentario
                  key={comentarioItem.id}
                  comentario={comentarioItem}
                  usuario={usuario}
                  tabulacao={0}
                />
              );
            })}

            <Comentar id={post.id} handleClick={handleClick} />
          </div>
        ) : null}

        <Snackbar
          open={valido}
          autoHideDuration={2000}
          onClose={handleCloseValido}
        >
          <Alert onClose={handleCloseValido} severity="success">
            Comentario enviado
          </Alert>
        </Snackbar>
      </div>
    );
  } else {
    return <div>carregando</div>;
  }
}
