import {
  Button,
  Modal,
  TextField,
  Typography,
  Switch,
  Backdrop,
  CircularProgress,
} from "@mui/material";
import { useState } from "react";
import { useFBApi } from "../../../hooks";
import "./criarPost.style.css";

export function CriarPost({ onClick }) {
  const [post, setPost] = useState({
    titulo: "",
    valor: "",
    descricao: "",
    imagem: "",
    isPrivado: false,
  });
  const [carregando, setCarregando] = useState(false);
  const [modal, setModal] = useState(false);
  const FBApi = useFBApi();

  function handleChangeForm(event) {
    const { value, name } = event.target;

    const novoPost = {
      ...post,
      [name]: value,
    };

    setPost(novoPost);
  }

  function handleChangeCheck() {
    const novoPost = {
      ...post,
      isPrivado: !post.isPrivado,
    };

    setPost(novoPost);
  }

  async function handleCriarPost(event) {
    event.preventDefault();

    setCarregando(true);

    await FBApi.criarPost(post);

    setCarregando(false);
    setModal(false);

    onClick();
  }

  function handleModal() {
    setModal(!modal);
  }

  return (
    <div className="criar_post">
      <Button fullWidth variant="contained" onClick={handleModal}>
        Criar o seu post!
      </Button>

      <Modal open={modal} onClose={handleModal} className="criar_post__modal">
        <form
          onSubmit={handleCriarPost}
          className="criar_post__form"
          autoComplete="off"
        >
          <TextField
            required
            onChange={handleChangeForm}
            value={post.titulo}
            name="titulo"
            label="Título"
          />
          <TextField
            onChange={handleChangeForm}
            value={post.valor}
            name="valor"
            label="Preço"
            type="number"
          />
          <div>
            <Typography variant="body1" component="p">
              Privado?
            </Typography>
            <Switch onChange={handleChangeCheck} checked={post.isPrivado} />
          </div>
          <TextField
            required
            onChange={handleChangeForm}
            value={post.descricao}
            name="descricao"
            label="Descrição"
            multiline
            rows={6}
            fullWidth
          />
          <TextField
            onChange={handleChangeForm}
            value={post.imagem}
            name="imagem"
            label="Imagem"
          />
          <Button type="submit" variant="contained">
            Postar
          </Button>
        </form>
      </Modal>

      <Backdrop
        sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={carregando}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    </div>
  );
}
