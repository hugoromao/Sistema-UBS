type HeaderProps = {
    titulo: string;
}

export default function Header({titulo}: HeaderProps) {
    return (
        <header className="w-full flex justify-content-between mt-2">
            <span className='text-2xl'>{titulo}</span>
            <div className="inline-flex align-items-center">
                <div className='pr-2'>Secretária</div>
                <div className='flex align-items-center gap-2 border-left-1 pl-2'>
                    <i className="pi pi-clock"></i>
                    <span>10:23</span>
                </div>
            </div>
        </header>
    )
}