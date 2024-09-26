import api from "@/services/serverApi";
import { NextApiRequest, NextApiResponse } from "next";

// src/pages/api/users.js
export default async function handler(req: NextApiRequest, res: NextApiResponse) {
    if (req.method === 'GET') {
        try {
            api.defaults.headers.common.Authorization = 'Bearer ' + req.headers.authorization;
            const response = await api.get("/profissional/listar");

            res.status(200).json({ profissionais: response.data });
        } catch (e) {
            console.error(e);
            res.status(500).json({ message: "Erro ao listar profissionais" });
        }
    } else {
        res.status(405).json({ message: 'Método não permitido' });
    }
}