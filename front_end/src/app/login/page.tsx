import { CardComponent } from "@/components/ui/login/CardComponent";

export default function login() {
  return (
    <div className="flex min-h-screen w-full">
      <div className="hidden w-1/2 bg-[#A60321] md:block" />
      <div className="flex w-full items-center justify-center bg-[#efe6d8] p-4 md:w-1/2">
        <CardComponent />
      </div>
    </div>
  );
}
