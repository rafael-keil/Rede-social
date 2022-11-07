import "./header.style.css";
import {
  createTheme,
  ThemeProvider,
  CircularProgress,
  TextField,
  Button,
  Modal,
  Typography,
  Snackbar,
  Alert,
} from "@mui/material";
import { CardUsuario } from "../cardUsuario/cardUsuario.component";
import { useEffect, useState } from "react";
import { useFBApi } from "../../../hooks/api/fb/useFBApi";
import { useGlobalUser } from "../../../context";
import { useHistory } from "react-router";
import { PATH } from "../../../constants";

export function Header() {
  const [usuarioLogado, setUsuarioLogado] = useState(null);
  const [pesquisa, setPesquisa] = useState("");
  const [valido, setValido] = useState(false);
  const [carregando, setCarregando] = useState(false);
  const [modal, setModal] = useState(false);
  const [listaUsuarios, setListaUsuarios] = useState("");
  const [, setGlobalUser] = useGlobalUser();
  const FBApi = useFBApi();
  const { push } = useHistory();

  useEffect(() => {
    async function getApi() {
      const novoUsuario = await FBApi.usuarioLogado();

      setUsuarioLogado(novoUsuario);
    }

    getApi();
  }, []);

  const whiteInput = createTheme({
    palette: {
      primary: {
        main: "#fff",
      },
      text: {
        primary: "#fff",
      },
    },
  });

  function handleLogout() {
    setGlobalUser("");
  }

  function handleMain() {
    push(PATH.MAIN);
  }

  async function handlePesquisar(event) {
    event.preventDefault();

    setModal(true);

    setCarregando(true);

    const newUsuarios = await FBApi.pesquisarUsuario(pesquisa);

    setCarregando(false);
    setValido(true);

    setListaUsuarios(newUsuarios);
  }

  function handleChange(event) {
    const { value } = event.target;

    setPesquisa(value);
  }

  function handleModal() {
    setModal(!modal);
    setListaUsuarios([]);
  }

  async function handleSolicitacao(email) {
    setCarregando(true);

    await FBApi.solicitarAmizade(email);

    setCarregando(false);

    setModal(false);
  }

  function handleCloseValido() {
    setValido(!valido);
  }

  return (
    <div className="header">
      <button className="header__nome" onClick={handleMain}>
        facebrick
      </button>

      <form
        onSubmit={handlePesquisar}
        className="header__pesquisa_content"
        autoComplete="off"
      >
        <ThemeProvider theme={whiteInput}>
          <TextField
            onChange={handleChange}
            value={pesquisa}
            name="pesquisa"
            label="Pesquise amigos aqui"
            variant="filled"
            InputLabelProps={{
              style: { color: "white" },
            }}
            className="header__pesquisa"
          />
          <Button type="submit">
            <img
              src="https://www.freeiconspng.com/uploads/arrow-icon-28.png"
              alt="pesquisar"
              className="header__enviar"
            />
          </Button>
        </ThemeProvider>
      </form>

      <div className="header__usuario">
        {usuarioLogado ? (
          <div className="header__card">
            <CardUsuario
              foto={usuarioLogado.imagemPerfil}
              nome={usuarioLogado.nome}
              color="white"
              email={usuarioLogado.email}
            />
            <Button onClick={handleLogout}>
              <img
                src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/OOjs_UI_icon_logOut-ltr.svg/1200px-OOjs_UI_icon_logOut-ltr.svg.png"
                alt="logout"
                className="header__logout"
              />
            </Button>
          </div>
        ) : (
          <CircularProgress color="inherit" />
        )}
      </div>

      <Modal open={modal} onClose={handleModal} className="header__modal">
        <div className="header__modal_item">
          {carregando ? (
            <CircularProgress color="inherit" />
          ) : listaUsuarios.length ? (
            listaUsuarios.map((usuario) => {
              return (
                <div key={usuario.email} className="header__pesquisa_item">
                  <CardUsuario
                    foto={usuario.imagemPerfil}
                    nome={usuario.nome}
                    email={usuario.email}
                  />

                  <Button onClick={() => handleSolicitacao(usuario.email)}>
                    Enviar Solicitação
                  </Button>
                </div>
              );
            })
          ) : (
            <Typography variant="h4" component="p">
              Nenhum usuario encontrado
            </Typography>
          )}
        </div>
      </Modal>

      <Snackbar
        open={valido}
        autoHideDuration={1000}
        onClose={handleCloseValido}
      >
        <Alert onClose={handleCloseValido} severity="success">
          Solicitação enviada
        </Alert>
      </Snackbar>
    </div>
  );
}
