"use client";

import * as React from "react";
import { ArrowRight, Eye, EyeOff, Lock, Mail } from "lucide-react";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export function AuthCard() {
  const [showPassword, setShowPassword] = React.useState(false);

  return (
    <Card className="w-full max-w-lg rounded-3xl border border-white bg-white p-3 shadow-x1">
      <CardHeader>
        <CardTitle className="font-heading text-3xl font-normal normal-case tracking-normal text-[#2b241d] text-center">
          Bem-vindo de volta
        </CardTitle>
        <CardDescription className="text-[#8a8177] text-center">
          Entre com sua conta para continuar sua leitura
        </CardDescription>
      </CardHeader>
      <CardContent>
        <form className="flex flex-col gap-5">
          <div className="grid gap-2">
            <Label
              htmlFor="email"
              className="text-xs font-semibold normal-case tracking-normal text-[#2b241d]"
            >
              Email
            </Label>
            <div className="relative flex items-center">
              <Mail className="pointer-events-none absolute left-3.5 size-4 text-[#a39a8d]" />
              <Input
                id="email"
                type="email"
                placeholder="seu@email.com"
                required
                className="h-11 w-full rounded-xl border border-[#e7ddcd] bg-white pr-4 pl-10 text-sm text-[#2b241d] placeholder:text-[#a39a8d] focus-visible:border-b-[#e7ddcd] focus-visible:ring-2 focus-visible:ring-[#8b1e2f]/20"
              />
            </div>
          </div>
          <div className="grid gap-2">
            <div className="flex items-center justify-between">
              <Label
                htmlFor="password"
                className="text-xs font-semibold normal-case tracking-normal text-[#2b241d]"
              >
                Senha
              </Label>
              <a
                href="#"
                className="text-sm font-medium text-[#A60321] hover:underline"
              >
                Esqueceu a senha?
              </a>
            </div>
            <div className="relative flex items-center">
              <Lock className="pointer-events-none absolute left-3.5 size-4 text-[#a39a8d]" />
              <Input
                id="password"
                type={showPassword ? "text" : "password"}
                required
                className="h-11 w-full rounded-xl border border-[#e7ddcd] bg-white pr-10 pl-10 text-sm text-[#2b241d] placeholder:text-[#a39a8d] focus-visible:border-b-[#e7ddcd] focus-visible:ring-2 focus-visible:ring-[#8b1e2f]/20"
              />
              <button
                type="button"
                onClick={() => setShowPassword((v) => !v)}
                className="absolute right-3.5 text-[#a39a8d] hover:text-[#2b241d]"
                aria-label={showPassword ? "Ocultar senha" : "Mostrar senha"}
              >
                {showPassword ? (
                  <EyeOff className="size-4" />
                ) : (
                  <Eye className="size-4" />
                )}
              </button>
            </div>
          </div>
          <Button
            type="submit"
            className="h-12 w-full gap-2 rounded-full border-none bg-[#A60321] text-sm font-semibold normal-case tracking-normal text-white hover:bg-[#75182a]"
          >
            Entrar
            <ArrowRight className="size-4" />
          </Button>
        </form>
      </CardContent>
      <CardFooter className="flex-col gap-4">
        <div className="relative flex w-full items-center justify-center">
          <span className="absolute inset-x-0 top-1/2 h-px bg-[#e7ddcd]" />
          <span className="relative bg-[#f7f1e6] px-3 text-xs text-[#a39a8d]">
            Novo por aqui?
          </span>
        </div>
        <a
          href="#"
          className="text-sm font-medium text-[#A60321] hover:underline"
        >
          Crie sua conta gratuitamente
        </a>
      </CardFooter>
    </Card>
  );
}
