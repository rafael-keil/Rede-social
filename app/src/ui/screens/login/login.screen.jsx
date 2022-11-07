import "./login.style.css";
import {
  CircularProgress,
  Backdrop,
  Snackbar,
  Alert,
  Typography,
  Button,
  TextField,
} from "@mui/material";
import { useState } from "react";
import { useFBApi } from "../../../hooks";
import { useHistory } from "react-router";
import { useGlobalUser } from "../../../context";
import { PATH } from "../../../constants";

export function Login() {
  const [, setGlobalUser] = useGlobalUser();
  const [isLogin, setIsLogin] = useState(true);
  const [erro, setErro] = useState(false);
  const [valido, setValido] = useState(false);
  const [carregando, setCarregando] = useState(false);
  const [cadastro, setCadastro] = useState({
    apelido: "",
    dataNascimento: "",
    email: "",
    imagemPerfil: "",
    nome: "",
    sobrenome: "",
    senha: "",
  });
  const [login, setLogin] = useState({ email: "", senha: "" });
  const FBApi = useFBApi();
  const { push } = useHistory();

  function handleChangeLogin(event) {
    const { value, name } = event.target;

    const newLogin = {
      ...login,
      [name]: value,
    };

    setLogin(newLogin);
  }

  function handleChangeCadastro(event) {
    const { value, name } = event.target;

    const newCadastro = {
      ...cadastro,
      [name]: value,
    };

    setCadastro(newCadastro);
  }

  function handleClickChange() {
    setIsLogin(!isLogin);
  }

  async function handleCadastro(event) {
    event.preventDefault();

    setCarregando(true);

    const response = await FBApi.cadastroUsuario(cadastro);

    if (response === undefined) {
      setErro(true);
    } else {
      setIsLogin(true);
      setValido(true);
    }

    setCarregando(false);
  }

  async function handleLogin(event) {
    event.preventDefault();

    setCarregando(true);

    try {
      const response = await FBApi.logarUsuario(login);

      setGlobalUser(response.token);

      push(PATH.MAIN);
    } catch {
      setErro(true);
    }

    setCarregando(false);
  }

  function handleCloseErro() {
    setErro(false);
  }

  function handleCloseValido() {
    setValido(false);
  }

  return (
    <div className="login">
      {!!isLogin ? (
        <div className="login__div">
          <Typography variant="h3" component="h3">
            Login
          </Typography>

          <form
            onSubmit={handleLogin}
            className="login__form"
            autoComplete="off"
          >
            <div className="login__form_input">
              <TextField
                required
                onChange={handleChangeLogin}
                value={login.email}
                type="email"
                name="email"
                label="E-mail"
              />
              <TextField
                required
                onChange={handleChangeLogin}
                value={login.senha}
                name="senha"
                label="Senha"
                type="password"
              />
            </div>
            <Button type="submit" variant="contained">
              logar
            </Button>
          </form>

          <Button onClick={handleClickChange} variant="outlined">
            Ainda não possui cadastro? Cadastrar-se
          </Button>
        </div>
      ) : (
        <div className="login__div">
          <Typography variant="h3" component="h3">
            Cadastro
          </Typography>

          <form
            onSubmit={handleCadastro}
            className="login__form"
            autoComplete="off"
          >
            <div className="login__form_input">
              <TextField
                required
                onChange={handleChangeCadastro}
                value={cadastro.nome}
                name="nome"
                label="Nome"
              />
              <TextField
                required
                onChange={handleChangeCadastro}
                value={cadastro.sobrenome}
                name="sobrenome"
                label="Sobrenome"
              />
              <TextField
                onChange={handleChangeCadastro}
                value={cadastro.apelido}
                name="apelido"
                label="Apelido"
              />
              <TextField
                required
                onChange={handleChangeCadastro}
                value={cadastro.dataNascimento}
                name="dataNascimento"
                label="Data de Nascimento"
                placeholder="dd/mm/yyyy"
              />
              <TextField
                required
                onChange={handleChangeCadastro}
                value={cadastro.email}
                type="email"
                name="email"
                label="E-mail"
              />
              <TextField
                required
                onChange={handleChangeCadastro}
                value={cadastro.senha}
                name="senha"
                label="Senha"
                type="password"
              />
              <TextField
                onChange={handleChangeCadastro}
                value={cadastro.imagemPerfil}
                name="imagemPerfil"
                label="Foto de perfil"
              />
            </div>
            <Button type="submit" variant="contained">
              Cadastrar-se
            </Button>
          </form>

          <Button onClick={handleClickChange} variant="outlined">
            Já possui cadastro? Logar
          </Button>
        </div>
      )}

      <Backdrop
        sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={carregando}
      >
        <CircularProgress color="inherit" />
      </Backdrop>

      <Snackbar open={erro} autoHideDuration={6000} onClose={handleCloseErro}>
        <Alert onClose={handleCloseErro} severity="error">
          Dados incorretos!
        </Alert>
      </Snackbar>

      <Snackbar
        open={valido}
        autoHideDuration={1000}
        onClose={handleCloseValido}
      >
        <Alert onClose={handleCloseErro} severity="success">
          Cadastro Efetuado!
        </Alert>
      </Snackbar>
    </div>
  );
}
