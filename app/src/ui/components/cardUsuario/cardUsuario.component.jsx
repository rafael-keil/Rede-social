import "./cardUsuario.style.css";
import { Avatar, Typography } from "@mui/material";
import { useHistory } from "react-router";

export function CardUsuario({ foto, nome, color, email }) {
  const { push } = useHistory();

  function handleClick() {
    push(`/perfil/${email}`);
    window.location.reload();
  }

  return (
    <button
      className={`card_usuario card_usuario__${color}`}
      onClick={handleClick}
    >
      <Avatar alt="Remy Sharp" src={foto} />
      <Typography variant="h5" component="p" className="card_usuario__nome">
        {nome}
      </Typography>
    </button>
  );
}
