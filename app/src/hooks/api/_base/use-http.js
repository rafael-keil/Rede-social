import { useGlobalUser } from "../../../context";
import { useAxios } from "./use-axios";

export function useHttp(baseURL, headers) {
  const [, setUser] = useGlobalUser();
  const instance = useAxios(baseURL, headers);

  async function get(url) {
    try {
      const response = await instance.get(url);

      return response.data;
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
    }
  }

  async function post(url, data) {
    try {
      const response = await instance.post(url, data);

      return response.data;
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
    }
  }

  async function put(url, data) {
    try {
      const response = await instance.put(url, data);

      return response.data;
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
    }
  }

  async function delet(url, data) {
    try {
      const response = await instance.delete(url, data);

      return response.data;
    } catch (error) {
      if (error.response.status === 401) {
        setUser({});
        localStorage.removeItem("user");
      }
    }
  }

  return {
    get,
    post,
    put,
    delet,
  };
}
