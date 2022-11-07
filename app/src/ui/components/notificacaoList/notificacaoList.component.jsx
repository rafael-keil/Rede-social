import { Button, CircularProgress, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { CardUsuario } from "..";
import { useFBApi } from "../../../hooks";
import "./notificacaoList.style.css";

export function NotificacaoList() {
  const [solicitacao, setSolicitacao] = useState([]);
  const [pagina, setPagina] = useState(0);
  const [carregando, setCarregando] = useState(true);
  const FBApi = useFBApi();

  useEffect(() => {
    async function getApi() {
      const newSolicitacao = await FBApi.listarSolicitacao(pagina);

      setSolicitacao([...solicitacao, ...newSolicitacao?.content]);

      setCarregando(false);
    }

    getApi();
  }, [pagina]);

  function handleCarregar() {
    setCarregando(true);
    setPagina(pagina + 1);
  }

  async function handleAceitar(id) {
    await FBApi.aceitarSolicitacao(id);

    setSolicitacao([]);
    setPagina(0);
  }

  async function handleCancelar(id) {
    await FBApi.deletarSolicitacao(id);

    setSolicitacao([]);
    setPagina(0);
  }

  return (
    <div className="amigos_list">
      <Typography variant="h5" component="h3">
        Solicitações de amizade:
      </Typography>

      {solicitacao.length
        ? solicitacao.map((soli) => {
            return (
              <div
                key={soli.remetente.email}
                className="notificacao__solicitacao"
              >
                <CardUsuario
                  foto={soli.remetente.imagemPerfil}
                  nome={soli.remetente.nome}
                  email={soli.remetente.email}
                />

                <div className="notificacao__button">
                  <Button fullWidth onClick={() => handleAceitar(soli.id)}>
                    Aceitar
                  </Button>
                  <Button fullWidth onClick={() => handleCancelar(soli.id)}>
                    Cancelar
                  </Button>
                </div>
              </div>
            );
          })
        : null}

      <div className="main__feed_carregando">
        {carregando ? (
          <CircularProgress color="inherit" />
        ) : (
          <Button fullWidth variant="contained" onClick={handleCarregar}>
            Carregar mais solicitações
          </Button>
        )}
      </div>
    </div>
  );
}
