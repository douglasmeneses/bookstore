import { AuthBrandPanel } from "@/components/ui/auth/AuthBrandPanel";
import { AuthCard } from "@/components/ui/auth/AuthCard";

export default function login() {
  return (
    <div
      className="flex w-full"
      style={{ zoom: 1.4, minHeight: "calc(100vh / 1.4)" }}
    >
      <AuthBrandPanel />
      <div className="flex w-full items-center justify-center bg-[#efe6d8] p-4 md:w-1/2">
        <AuthCard />
      </div>
    </div>
  );
}
