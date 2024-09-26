import NextHead from "next/head"

type HeadProps = {
    nomePagina: string;
}

export default function Head({ nomePagina }: HeadProps) {

    const tituloPagina = `UBS - ${nomePagina}`

    return (
        <NextHead>
            <title>{tituloPagina}</title>
            <meta name="description" content="Página inicial do sistema" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <link rel="icon" href="./images/ufrr-32x32.png" sizes='32x32' />
            <link rel="icon" href="./images/ufrr-192x192.png" sizes='192x192' />
        </NextHead>
    )
}