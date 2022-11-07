import { Redirect, Route } from "react-router";
import { PATH } from "../../../constants";
import { useGlobalUser } from "../../../context";

export function PrivateRoute({ path, children }) {
  const [user] = useGlobalUser();

  if (!user) {
    return <Redirect to={PATH.LOGIN} />;
  }

  return (
    <Route path={path} exact>
      {children}
    </Route>
  );
}
