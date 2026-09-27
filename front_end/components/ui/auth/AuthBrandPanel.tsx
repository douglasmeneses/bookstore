import { BookOpen } from "lucide-react";

export function AuthBrandPanel() {
  return (
    <div className="relative hidden w-1/2 overflow-hidden bg-gradient-to-br from-[#C4082A] via-[#A60321] to-[#7A0218] p-12 md:flex md:flex-col md:justify-between">
      <div
        aria-hidden
        className="pointer-events-none absolute top-1/2 right-[-140px] size-[420px] -translate-y-1/2 rounded-full border border-white/10"
      />
      <div
        aria-hidden
        className="pointer-events-none absolute top-1/2 right-[-80px] size-[300px] -translate-y-1/2 rounded-full border border-white/10"
      />

      <div className="flex items-center gap-3">
        <div className="flex size-11 items-center justify-center rounded-full bg-white/10">
          <BookOpen className="size-5 text-white" />
        </div>
        <span className="font-heading text-xl text-white">Book Store</span>
      </div>

      <div className="flex flex-col gap-6">
        <h1 className="font-heading max-w-md text-5xl leading-[1.1] text-white">
          Sua próxima história começa aqui.
        </h1>
        <p className="max-w-sm text-base leading-relaxed text-white/70">
          Milhares de títulos esperando por você — dos clássicos aos
          lançamentos.
        </p>
        <blockquote className="max-w-md rounded-2xl bg-white/10 p-6 backdrop-blur-sm">
          <p className="text-white italic">
            &ldquo;Um leitor vive mil vidas antes de morrer.&rdquo;
          </p>
          <footer className="mt-2 text-sm text-white/60">
            — George R. R. Martin
          </footer>
        </blockquote>
      </div>

      <span className="text-xs text-white/50">
        © 2026 Book Store · Livraria on-line
      </span>
    </div>
  );
}
