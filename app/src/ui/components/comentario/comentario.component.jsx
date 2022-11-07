import "./comentario.style.css";
import {
  Button,
  Typography,
  ButtonGroup,
  CircularProgress,
  Snackbar,
  Alert,
} from "@mui/material";
import { useEffect, useState } from "react";
import { useFBApi } from "../../../hooks";
import { CardUsuario, Comentar } from "..";

export function Comentario({ comentario, usuario, tabulacao }) {
  const [curtido, setCurtido] = useState(false);
  const [comentarioOpen, setComentarioOpen] = useState(false);
  const [comentarioList, setComentarioList] = useState([]);
  const [valido, setValido] = useState(false);
  const [carregando, setCarregando] = useState(false);
  const [curtidaExtra, setCurtidaExtra] = useState(0);
  const FBApi = useFBApi();

  useEffect(() => {
    const newCurtido = comentario.curtida.some(
      (curtidaItem) => curtidaItem.autor.email === usuario?.email
    );

    setCurtido(newCurtido);
  }, []);

  function handleCurtir() {
    curtido
      ? setCurtidaExtra(curtidaExtra - 1)
      : setCurtidaExtra(curtidaExtra + 1);

    setCurtido(!curtido);

    try {
      FBApi.curtir(comentario.id);
    } catch {
      setCurtido(!curtido);
    }
  }

  async function handleComentario() {
    setComentarioOpen(!comentarioOpen);
    setCarregando(true);

    if (!comentarioList.length) {
      const novosComentario = await FBApi.buscarComentarios(comentario.id);

      setComentarioList(novosComentario);
    }

    setCarregando(false);
  }

  function handleClick() {
    setComentarioList([]);

    setValido(true);

    handleComentario();
  }

  function handleCloseValido() {
    setValido(!valido);
  }

  return (
    <div className={`comentario comentario__tab${tabulacao}`}>
      <div className="comentario__header">
        <CardUsuario
          foto={comentario.autor.imagemPerfil}
          nome={comentario.autor.nome}
          email={comentario.autor.email}
        />

        <Typography variant="body1" component="p">
          {`${comentario.data[1]}/${comentario.data[2]}/${comentario.data[0]} ${comentario.data[3]}:${comentario.data[4]}:${comentario.data[5]}`}
        </Typography>
      </div>

      <div className="comentario__corpo">
        {comentario.imagem ? (
          <img
            className="comentario__imagem"
            src={comentario.imagem}
            alt="imagem do comentario"
          />
        ) : null}

        <Typography
          variant="body1"
          component="p"
          className="comentario_descricao"
        >
          {comentario.descricao}
        </Typography>
      </div>

      <div className="comentario__interacao">
        <div className="comentario_contagem">
          <Typography
            variant="body2"
            component="p"
            className="comentario_descricao"
          >
            Curtidas: {comentario.curtida?.length + curtidaExtra}
          </Typography>
        </div>

        <ButtonGroup fullWidth variant="outlined">
          {curtido ? (
            <Button onClick={handleCurtir} size="small" variant="contained">
              Descurtir
            </Button>
          ) : (
            <Button onClick={handleCurtir} size="small">
              Curtir
            </Button>
          )}
          {comentarioOpen ? (
            <Button onClick={handleComentario} variant="contained" size="small">
              Fechar Comentarios
            </Button>
          ) : (
            <Button onClick={handleComentario} size="small">
              Abrir Comentarios
            </Button>
          )}
        </ButtonGroup>
      </div>

      {carregando ? (
        <div className="comentario__loader">
          <CircularProgress color="inherit" />
        </div>
      ) : comentarioOpen ? (
        <div className="comentario__comentario">
          {comentarioList?.map((comentarioItem) => {
            return (
              <Comentario
                key={comentarioItem.id}
                comentario={comentarioItem}
                usuario={usuario}
                tabulacao={Math.min(tabulacao + 1, 1)}
              />
            );
          })}

          <Comentar id={comentario.id} handleClick={handleClick} />
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
}
