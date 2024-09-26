import { Image } from "primereact/image";
import { MenuItem } from "primereact/menuitem";
import { Menu } from "primereact/menu"
import Router from "next/router";
import { destroyCookie } from "nookies"

const items: MenuItem[] = [
    {
        label: "Inicio",
        icon: "pi pi-home",
        url: "",
        command: () => { Router.push("/") },
    },
    {
        label: "Consultas",
        icon: "pi pi-calendar",
        url: "",
        command: () => { Router.push("/consultas") },
    },
    {
        label: "Pacientes",
        icon: "pi pi-users",
        url: "",
        command: () => { Router.push("/pacientes") },
    },
    {
        label: "Sair",
        icon: "pi pi-sign-out",
        style: {
            position: "absolute",
            bottom: 0,
            marginBottom: "20px",
        },
        command: () => { logout() },
    }
]

export default function Sidebar() {
    return (
        <div className="sidebar">
            <div className="logo px-4">
                <div className="flex justify-content-center mt-5">
                    <Image
                        src="./images/ufrr-192x192.png"
                        alt="logo ufrr"
                        width="100px"
                        height="100px"
                    />
                </div>
                <div className="text-center font-bold">UFRR</div>
                <div className="text-center text-2xl font-light mt-2">Unidade Básica de Saúde</div>
            </div>
            <Menu model={items} className="mt-4 mr-0 flex justify-content-center" />
        </div>
    )
}

function logout() {
    destroyCookie(undefined, "ubs-access_token");
    Router.push("/login")
}