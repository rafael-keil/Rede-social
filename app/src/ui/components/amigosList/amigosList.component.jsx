import { Button, CircularProgress, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { CardUsuario } from "..";
import { useFBApi } from "../../../hooks";
import "./amigosList.style.css";

export function AmigosList() {
  const [amigos, setAmigos] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [carregando, setCarregando] = useState(true);
  const FBApi = useFBApi();

  useEffect(() => {
    async function getApi() {
      const newAmigos = await FBApi.listarAmigos(pagina);

      setAmigos([...amigos, ...newAmigos?.content]);

      setCarregando(false);
    }

    getApi();
  }, [pagina]);

  function handleCarregar() {
    setCarregando(true);
    setPagina(pagina + 1);
  }

  return (
    <div className="amigos_list">
      <Typography variant="h5" component="h5">
        Amigos:
      </Typography>

      {amigos.length
        ? amigos.map((amigo) => {
            return (
              <CardUsuario
                key={amigo.email}
                foto={amigo.imagemPerfil}
                nome={amigo.nome}
                email={amigo.email}
              />
            );
          })
        : null}

      <div className="main__feed_carregando">
        {carregando ? (
          <CircularProgress color="inherit" />
        ) : (
          <Button fullWidth variant="contained" onClick={handleCarregar}>
            Carregar mais amigos
          </Button>
        )}
      </div>
    </div>
  );
}
