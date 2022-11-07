import { Redirect, Route, Switch } from "react-router";
import "./App.css";
import { PATH } from "./constants";
import { PrivateRoute } from "./ui/components";
import { Login, Main, Perfil } from "./ui/screens";

function App() {
  return (
    <Switch>
      <Route path={PATH.LOGIN} exact>
        <Login />
      </Route>
      <PrivateRoute path={PATH.MAIN} exact>
        <Main />
      </PrivateRoute>
      <PrivateRoute path={PATH.PERFIL} exact>
        <Perfil />
      </PrivateRoute>
      <Route path="/">
        <Redirect to={PATH.LOGIN} />
      </Route>
    </Switch>
  );
}

export default App;
