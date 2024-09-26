import api from "@/services/serverApi";
import { NextApiRequest, NextApiResponse } from "next";

// src/pages/api/users.js
export default async function handler(req: NextApiRequest, res: NextApiResponse) {
  if (req.method === 'POST') {
    const data = req.body;

    try {
      const response = await api.post("/authenticate", { ...data }, {
        headers: { "Content-Type": "application/json" }
      });

      res.status(200).json({ token: response.data.token });
    } catch (e) {
      console.error(e);
      res.status(500).json({ message: "Erro ao realizar login"});
    }
  } else {
    res.status(405).json({ message: 'Método não permitido' });
  }
}