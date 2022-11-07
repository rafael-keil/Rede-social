import { Button, Modal, TextField } from "@mui/material";
import { useState } from "react";
import { useFBApi } from "../../../hooks";
import "./comentar.style.css";

export function Comentar({ id, handleClick }) {
  const [modal, setModal] = useState(false);
  const [comentario, setComentario] = useState({
    descricao: "",
    imagem: "",
  });
  const FBApi = useFBApi();

  function handleChangeForm(event) {
    const { value, name } = event.target;

    const novoComentario = {
      ...comentario,
      [name]: value,
    };

    setComentario(novoComentario);
  }

  function handleCriarPost(event) {
    event.preventDefault();

    setModal(false);

    handleClick();

    FBApi.criarComentario(comentario, id);
  }

  function handleModal() {
    setModal(!modal);
  }

  return (
    <div className="comentar">
      <Button fullWidth variant="outlined" onClick={handleModal}>
        Comentar
      </Button>

      <Modal open={modal} onClose={handleModal} className="criar_post__modal">
        <form
          onSubmit={handleCriarPost}
          className="comentar__form"
          autoComplete="off"
        >
          <TextField
            required
            onChange={handleChangeForm}
            value={comentario.descricao}
            name="descricao"
            label="Descrição"
            multiline
            rows={6}
            fullWidth
          />
          <TextField
            onChange={handleChangeForm}
            value={comentario.imagem}
            name="imagem"
            label="Imagem"
          />
          <Button type="submit" variant="contained">
            Postar
          </Button>
        </form>
      </Modal>
    </div>
  );
}
